package br.com.acta.vinylpgapi.dto.auth;

public record TokenResp(
        String token,
        String tokenType,
        Long expiresIn,
        Long userId
) {
}
