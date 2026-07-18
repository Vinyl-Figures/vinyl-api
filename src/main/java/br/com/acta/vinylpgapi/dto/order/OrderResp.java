package br.com.acta.vinylpgapi.dto.order;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderResp(
        Long id,
        Long userId,
        BigDecimal totalPrice,
        OffsetDateTime createdAt,
        List<OrderItemResp> items
) {
}
