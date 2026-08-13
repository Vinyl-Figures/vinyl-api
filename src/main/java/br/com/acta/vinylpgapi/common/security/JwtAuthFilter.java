package br.com.acta.vinylpgapi.common.security;

import br.com.acta.vinylpgapi.dto.ApiError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    public static final String USER_ID = "userId";

    private static final List<Route> PUBLIC_ROUTES = List.of(
            new Route("POST", "/api/v1/users"),
            new Route("POST", "/api/v1/auth/tokens")
    );

    private final JwtService service;
    private final ObjectMapper mapper;

    private record Route(String method, String path) {
        boolean matches(HttpServletRequest request) {
            return method.equalsIgnoreCase(request.getMethod()) && path.equals(request.getRequestURI());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return CorsUtils.isPreFlightRequest(request);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (isPublicRoute(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            reject(response);
            return;
        }

        try {
            String token = authorization.substring("Bearer ".length());
            Long userId = service.userIdConverter(token);

            request.setAttribute(USER_ID, userId);
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            reject(response);
        }
    }

    private boolean isPublicRoute(HttpServletRequest request) {
        return PUBLIC_ROUTES.stream()
                .anyMatch(route -> route.matches(request));
    }

    private void reject(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.getWriter().write(mapper.writeValueAsString(
                new ApiError(
                        List.of("A valid bearer token is required"),
                        null
                )
        ));
    }
}