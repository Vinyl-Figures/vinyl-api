package br.com.acta.vinylpgapi.dto.shipping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BrasilApiCepResp(
        String cep,
        String state,
        String city,
        Location location
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Location(
            String type,
            Coordinates coordinates
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Coordinates(
            String longitude,
            String latitude
    ) {
    }
}
