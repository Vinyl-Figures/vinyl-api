package br.com.acta.vinylpgapi.dto.order;

import java.math.BigDecimal;

public record OrderItemResp(
        Long id,
        Long vinylId,
        BigDecimal priceAtPurchase
) {
}
