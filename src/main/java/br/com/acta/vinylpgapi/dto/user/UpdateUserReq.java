package br.com.acta.vinylpgapi.dto.user;

import jakarta.validation.constraints.Size;

public record UpdateUserReq(
        @Size(max = 255)
        String name,
        String cellphone,
        String email,
        String password
) {
}
