package br.com.acta.vinylpgapi.dto.order;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderResp(
        Long id,
        Long userId,
        BigDecimal totalPrice,
        BigDecimal shippingPrice,
        String couponCode,
        OffsetDateTime createdAt,
        List<OrderItemResp> items
) {
}
