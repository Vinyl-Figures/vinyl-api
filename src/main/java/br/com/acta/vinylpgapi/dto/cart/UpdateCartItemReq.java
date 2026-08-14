package br.com.acta.vinylpgapi.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCartItemReq(
        @NotNull
        @Min(1)
        Integer quantity
) {
}
