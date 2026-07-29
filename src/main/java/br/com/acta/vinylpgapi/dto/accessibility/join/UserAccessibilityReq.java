package br.com.acta.vinylpgapi.dto.accessibility.join;

import jakarta.validation.constraints.NotNull;

public record UserAccessibilityReq(
        @NotNull
        Long accessibilityId
) {
}
