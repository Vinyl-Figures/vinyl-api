package br.com.acta.vinylpgapi.dto.payment;

import br.com.acta.vinylpgapi.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdatePaymentStatusReq(
        @NotNull
        PaymentStatus status
) {
}
