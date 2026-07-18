package br.com.acta.vinylpgapi.dto.vinyl;

import br.com.acta.vinylpgapi.dto.artist.ArtistResp;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;

import java.math.BigDecimal;
import java.util.List;

public record VinylResp(
        Long id,
        String title,
        BigDecimal price,
        String description,
        String releasedAt,
        String imageUrl,
        List<GenreResp> genres,
        List<ArtistResp> artists
) {
    public VinylResp(Long id, String title, BigDecimal price, String description, String releasedAt, String imageUrl) {
        this(id, title, price, description, releasedAt, imageUrl, List.of(), List.of());
    }
}