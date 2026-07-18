package br.com.acta.vinylpgapi.dto.vinyl;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateVinylReq(
        @Size(max = 60)
        String title,

        @Positive
        BigDecimal price,

        @Size(max = 200)
        String description,

        @Pattern(regexp = "^[0-9]{4}$") String releasedAt,
        String imageUrl
) {
    }
