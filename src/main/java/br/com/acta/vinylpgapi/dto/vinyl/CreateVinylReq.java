package br.com.acta.vinylpgapi.dto.vinyl;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateVinylReq(
        @NotBlank
        @Size(max = 60)
        String title,
        @NotNull
        @Positive
        BigDecimal price,
        @Size(max = 200)
        String description,
        @NotBlank
        @Pattern(regexp = "^[0-9]{4}$") String releasedAt,
        String imageUrl
) {
}
