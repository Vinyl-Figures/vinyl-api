package br.com.acta.vinylpgapi.dto.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenreReq(
        @NotBlank
        @Size(max = 50)
        String name
) {
}
