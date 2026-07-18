package br.com.acta.vinylpgapi.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserReq(
        @NotBlank
        @Size(max = 255)
        String name,

        @NotBlank
        @Pattern(regexp = "^[0-9]{11}$")
        String document,

        @NotBlank
        String cellphone,

        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
