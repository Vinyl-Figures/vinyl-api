package br.com.acta.vinylpgapi.dto.artist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateArtistReq(
        @NotBlank
        @Size(max = 60)
        String name,
        String description
) {
}
