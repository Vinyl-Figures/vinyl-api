package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.ValidationException;
import br.com.acta.vinylpgapi.dto.shipping.ShippingResp;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Sem geocoding de verdade: a "distância" é derivada do CEP de forma
// determinística (mesmo CEP sempre dá o mesmo valor), só pra ter uma
// regra plugável até existir uma origem/API de distância real.
@Service
public class ShippingService {
    private static final BigDecimal RATE_PER_KM = new BigDecimal("1.50");
    private static final BigDecimal BASE_KM = new BigDecimal("5");

    public ShippingResp calculate(String zipCode) {
        if (zipCode == null || !zipCode.matches("^[0-9]{8}$")) {
            throw new ValidationException("zipCode must have 8 digits");
        }

        long ultimosTres = Long.parseLong(zipCode.substring(5));
        BigDecimal distanceKm = BASE_KM.add(BigDecimal.valueOf(ultimosTres % 100));
        BigDecimal price = distanceKm.multiply(RATE_PER_KM).setScale(2, RoundingMode.HALF_UP);

        return new ShippingResp(zipCode, distanceKm, price);
    }
}
