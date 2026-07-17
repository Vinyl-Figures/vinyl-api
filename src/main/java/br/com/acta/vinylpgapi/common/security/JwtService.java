package br.com.acta.vinylpgapi.common.security;

public interface JwtService {
    String generateToken(Long userId);
    Long getExpirationSeconds();
}
