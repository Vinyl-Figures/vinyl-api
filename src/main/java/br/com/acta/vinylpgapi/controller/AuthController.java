package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.auth.LoginReq;
import br.com.acta.vinylpgapi.dto.auth.TokenResp;
import br.com.acta.vinylpgapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/tokens")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CurrentUser currentUser;

    @PostMapping
    public ResponseEntity<TokenResp> login(@Valid @RequestBody LoginReq dto){
        return ResponseEntity.status(201).body(authService.login(dto));
    }

    @DeleteMapping("/current")
    public ResponseEntity<Void> logout(){
        authService.logout(currentUser.getUserId());
        return ResponseEntity.noContent().build();
    }
}
