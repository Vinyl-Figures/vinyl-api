package br.com.acta.vinylpgapi.dto.cart;

import java.util.List;
import java.util.Map;

public record AddCartItemResp(
        List<CartItemResp> created,
        Map<Long, String> skipped
) {
}
