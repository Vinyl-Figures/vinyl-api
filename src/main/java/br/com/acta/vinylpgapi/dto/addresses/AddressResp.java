package br.com.acta.vinylpgapi.dto.addresses;

public record AddressResp(
        Long id,
        String number,
        String complement,
        String zipCode,
        Long userId
) {
}
