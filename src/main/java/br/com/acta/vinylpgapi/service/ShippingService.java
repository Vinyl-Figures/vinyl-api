package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.ValidationException;
import br.com.acta.vinylpgapi.dto.shipping.BrasilApiCepResp;
import br.com.acta.vinylpgapi.dto.shipping.ShippingResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ShippingService {
    private static final String CEP_REGEX = "^[0-9]{8}$";
    private static final double EARTH_RADIUS_KM = 6371.0;

    // RestClient.create() usa o java.net.http.HttpClient do JDK por padrão,
    // que carrega toda a stack de TLS/HTTP2 sob demanda — o suficiente pra
    // estourar o -XX:MaxMetaspaceSize=96m do Dockerfile (OutOfMemoryError:
    // Metaspace na primeira chamada HTTPS). SimpleClientHttpRequestFactory
    // usa HttpURLConnection, bem mais leve em classes carregadas.
    private final RestClient brasilApi = RestClient.builder()
            .baseUrl("https://brasilapi.com.br/api/cep/v2")
            .requestFactory(new SimpleClientHttpRequestFactory())
            .build();

    private final String originZipCode;
    private final BigDecimal basePrice;
    private final BigDecimal pricePerKm;

    public ShippingService(
            @Value("${app.shipping.origin-zip-code}") String originZipCode,
            @Value("${app.shipping.base-price}") BigDecimal basePrice,
            @Value("${app.shipping.price-per-km}") BigDecimal pricePerKm
    ) {
        this.originZipCode = originZipCode;
        this.basePrice = basePrice;
        this.pricePerKm = pricePerKm;
    }

    public ShippingResp calculate(String zipCode) {
        if (zipCode == null || !zipCode.matches(CEP_REGEX)) {
            throw new ValidationException("zipCode must have 8 digits");
        }

        double[] origin = geocode(originZipCode);
        double[] destination = geocode(zipCode);

        BigDecimal distanceKm = BigDecimal.valueOf(haversineKm(origin, destination))
                .setScale(1, RoundingMode.HALF_UP);

        BigDecimal price = basePrice.add(pricePerKm.multiply(distanceKm))
                .setScale(2, RoundingMode.HALF_UP);

        return new ShippingResp(zipCode, distanceKm, price);
    }

    private double[] geocode(String zipCode) {
        BrasilApiCepResp resp;
        try {
            resp = brasilApi.get().uri("/{cep}", zipCode).retrieve().body(BrasilApiCepResp.class);
        } catch (RestClientResponseException e) {
            throw new ValidationException("CEP " + zipCode + " not found");
        }

        String latitude = resp != null && resp.location() != null ? resp.location().coordinates().latitude() : null;
        String longitude = resp != null && resp.location() != null ? resp.location().coordinates().longitude() : null;

        if (latitude == null || latitude.isBlank() || longitude == null || longitude.isBlank()) {
            throw new ValidationException("CEP " + zipCode + " has no known coordinates");
        }

        return new double[]{Double.parseDouble(latitude), Double.parseDouble(longitude)};
    }

    private double haversineKm(double[] from, double[] to) {
        double dLat = Math.toRadians(to[0] - from[0]);
        double dLon = Math.toRadians(to[1] - from[1]);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(from[0])) * Math.cos(Math.toRadians(to[0]))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
