package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.dto.shipping.ShippingResp;
import br.com.acta.vinylpgapi.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {
    private final ShippingService service;

    @GetMapping
    public ResponseEntity<ShippingResp> calculate(@RequestParam String zipCode) {
        return ResponseEntity.ok(service.calculate(zipCode));
    }
}
