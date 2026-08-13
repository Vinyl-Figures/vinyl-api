package br.com.acta.vinylpgapi.dto.coupon;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateCouponReq(
        @NotBlank
        @Size(max = 30)
        String code,

        @NotNull
        @DecimalMin(value = "0", inclusive = false)
        @DecimalMax(value = "100")
        BigDecimal discountPercent
) {
}
