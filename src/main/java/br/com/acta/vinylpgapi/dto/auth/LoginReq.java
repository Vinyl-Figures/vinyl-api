package br.com.acta.vinylpgapi.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginReq(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
