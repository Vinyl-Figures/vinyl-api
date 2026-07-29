package br.com.acta.vinylpgapi.dto.order;

import jakarta.validation.constraints.NotNull;

public record OrderReq(
        @NotNull
        Long userId
) {
}
