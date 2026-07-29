package br.com.acta.vinylpgapi.dto.artist;

import jakarta.validation.constraints.Size;

public record UpdateArtistReq(
        @Size(max = 60)
        String name,
        String description
) {
}
