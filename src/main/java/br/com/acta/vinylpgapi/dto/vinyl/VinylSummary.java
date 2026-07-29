package br.com.acta.vinylpgapi.dto.vinyl;

import java.math.BigDecimal;

public record VinylSummary(
        Long id,
        String title,
        BigDecimal price
) {
}
