package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.UnauthorizedException;
import br.com.acta.vinylpgapi.common.security.JwtService;
import br.com.acta.vinylpgapi.dto.auth.LoginReq;
import br.com.acta.vinylpgapi.dto.auth.TokenResp;
import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.repository.UserRepository;
import br.com.acta.vinylpgapi.utils.Hash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final JwtService jwtService;

    public TokenResp login(LoginReq dto){
        User user = userRepo.findByEmail(dto.email())
                .filter(u -> Hash.validatePassword(u.getPassword(), dto.password()))
                .orElseThrow(() -> new UnauthorizedException("E-mail or password is incorrect"));

        String token = jwtService.generateToken(user.getId());
        return new TokenResp(token, "Bearer", jwtService.getExpirationSeconds(), user.getId());
    }

    public void logout(Long calledUserId){}
}
