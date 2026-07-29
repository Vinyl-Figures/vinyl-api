package br.com.acta.vinylpgapi.dto.payment;

import br.com.acta.vinylpgapi.enums.PaymentMethod;
import br.com.acta.vinylpgapi.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PaymentResp (
        Long id,
        BigDecimal value,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        OffsetDateTime createdAt,
        Long userId,
        Long orderId
){}
