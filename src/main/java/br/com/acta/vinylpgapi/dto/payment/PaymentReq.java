package br.com.acta.vinylpgapi.dto.payment;

import br.com.acta.vinylpgapi.enums.PaymentMethod;
import br.com.acta.vinylpgapi.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentReq(
        @NotNull
        Long userId,
        Long orderId,
        @NotNull
        @Positive
        BigDecimal value,
        @NotNull
        PaymentMethod paymentMethod,
        @NotNull
        PaymentStatus status
) {
}
