package br.com.acta.vinylpgapi.dto.shipping;

import java.math.BigDecimal;

public record ShippingResp(
        String zipCode,
        BigDecimal distanceKm,
        BigDecimal price
) {
}
