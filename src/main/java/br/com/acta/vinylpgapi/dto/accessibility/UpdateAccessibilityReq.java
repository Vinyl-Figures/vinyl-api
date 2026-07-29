package br.com.acta.vinylpgapi.dto.accessibility;

import jakarta.validation.constraints.Size;

public record UpdateAccessibilityReq(
        @Size(max = 255)
        String name,
        String description
) {
}
