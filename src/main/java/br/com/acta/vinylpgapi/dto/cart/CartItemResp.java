package br.com.acta.vinylpgapi.dto.cart;

import br.com.acta.vinylpgapi.dto.vinyl.VinylSummary;

public record CartItemResp(
        Long id,
        Long userId,
        Long vinylId,
        VinylSummary vinyl
) {
}
