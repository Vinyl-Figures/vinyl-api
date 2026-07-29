package br.com.acta.vinylpgapi.dto.genre.join;

import jakarta.validation.constraints.NotNull;

public record FavoriteGenreReq (
        @NotNull
        Long genreId
) {
}
