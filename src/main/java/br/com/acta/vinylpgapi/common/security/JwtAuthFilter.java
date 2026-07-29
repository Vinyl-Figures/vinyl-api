package br.com.acta.vinylpgapi.common.security;

import br.com.acta.vinylpgapi.dto.ApiError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // represents a route that can be accessed without authentication
    private record Route(String method, String path) {
        boolean matches(HttpServletRequest req){
            return method.equalsIgnoreCase(req.getMethod()) && path.equals(req.getRequestURI());
        }
    }

    public static final String USER_ID = "userId";

    // routes that does not need token check
    public static final List<Route> PUBLIC_ROUTES = List.of(
            new Route("POST", "/api/v1/users"),
            new Route("POST", "/api/v1/auth/tokens")
    );
    private final JwtService service;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // does not check if the route is public
        if (isPublicRoute(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // checks for a valid token in the header
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            reject(response);
            return;
        }

        // send requisition to controller
        try {
            Long userId = service.userIdConverter(header.substring("Bearer ".length()));
            request.setAttribute(USER_ID, userId);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            reject(response);
        }
    }

    private boolean isPublicRoute(HttpServletRequest request) {
        return PUBLIC_ROUTES.stream().anyMatch(r -> r.matches(request));
    }

    private void reject(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // error 401
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(mapper.writeValueAsString(
                new ApiError(List.of("A valid bearer token is required"), null)
        ));
    }
}
