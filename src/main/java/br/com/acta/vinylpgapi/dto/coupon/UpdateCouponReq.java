package br.com.acta.vinylpgapi.dto.coupon;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateCouponReq(
        @Size(max = 30)
        String code,

        @DecimalMin(value = "0", inclusive = false)
        @DecimalMax(value = "100")
        BigDecimal discountPercent
) {
}
