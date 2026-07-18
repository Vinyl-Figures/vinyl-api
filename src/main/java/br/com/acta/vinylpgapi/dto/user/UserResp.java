package br.com.acta.vinylpgapi.dto.user;

public record UserResp(
        Long id,
        String name,
        String document,
        String cellphone,
        String email
) {
}
