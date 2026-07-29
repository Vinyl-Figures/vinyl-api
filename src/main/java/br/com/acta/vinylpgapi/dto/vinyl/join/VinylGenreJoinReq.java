package br.com.acta.vinylpgapi.dto.vinyl.join;

import jakarta.validation.constraints.NotNull;

public record VinylGenreJoinReq(
        @NotNull
        Long genreId
) {
}
