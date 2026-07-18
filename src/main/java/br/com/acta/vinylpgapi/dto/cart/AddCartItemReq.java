package br.com.acta.vinylpgapi.dto.cart;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddCartItemReq(
        @NotEmpty
        List<@NotNull Long> vinylIds
) {
}
