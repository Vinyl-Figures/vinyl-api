package br.com.acta.vinylpgapi.dto.accessibility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAccessibilityReq(
        @NotBlank
        @Size(max = 255)
        String name,
        String description
) {
}
