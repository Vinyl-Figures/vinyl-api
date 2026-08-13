package br.com.acta.vinylpgapi.dto.coupon;

import java.math.BigDecimal;

public record CouponResp(
        Long id,
        String code,
        BigDecimal discountPercent
) {
}
