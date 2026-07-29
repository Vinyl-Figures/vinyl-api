package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.payment.PaymentReq;
import br.com.acta.vinylpgapi.dto.payment.PaymentResp;
import br.com.acta.vinylpgapi.dto.payment.UpdatePaymentStatusReq;
import br.com.acta.vinylpgapi.enums.PaymentMethod;
import br.com.acta.vinylpgapi.enums.PaymentStatus;
import br.com.acta.vinylpgapi.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;
    private final CurrentUser currentUser;

    @GetMapping
    public ResponseEntity<List<PaymentResp>> list(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long orderId, @RequestParam(required = false) PaymentStatus status, @RequestParam(required = false)PaymentMethod paymentMethod){
        return ResponseEntity.ok(service.list(currentUser.getUserId(), userId, orderId, status, paymentMethod));
    }

    @PostMapping
    public ResponseEntity<PaymentResp> create(@RequestBody @Valid PaymentReq dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResp> get(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id, currentUser.getUserId()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PaymentResp> updateStatus(@PathVariable Long id, @RequestBody @Valid UpdatePaymentStatusReq dto){
        return ResponseEntity.ok(service.updateStatus(id, currentUser.getUserId(), dto));
    }
}
