package br.com.acta.vinylpgapi.dto.addresses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressReq(
        @NotBlank
        @Size(max = 12)
        String number,
        String complement,
        @NotBlank
        @Pattern(regexp = "^[0-9]{8}$")
        String zipCode
) {
}
