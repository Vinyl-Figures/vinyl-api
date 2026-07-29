package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.payment.PaymentReq;
import br.com.acta.vinylpgapi.dto.payment.PaymentResp;
import br.com.acta.vinylpgapi.dto.payment.UpdatePaymentStatusReq;
import br.com.acta.vinylpgapi.enums.PaymentMethod;
import br.com.acta.vinylpgapi.enums.PaymentStatus;
import br.com.acta.vinylpgapi.model.Order;
import br.com.acta.vinylpgapi.model.Payment;
import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.repository.PaymentRepository;
import br.com.acta.vinylpgapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repo;
    private final UserService userService;
    private final OrderService orderService;

    public PaymentResp create(PaymentReq dto){
        User user = userService.getEntity(dto.userId());
        Order order = orderService.getEntity(dto.orderId(), dto.userId());

        Payment payment = new Payment(dto.value(), dto.paymentMethod(), dto.status(), user, order);
        return toResp(repo.save(payment));
    }

    public PaymentResp getById(Long paymentId, Long calledUserId){
        Payment payment = getEntity(paymentId, calledUserId);
        return toResp(payment);
    }

    public List<PaymentResp> list(Long calledUserId, Long userIdFilter, Long orderIdFilter, PaymentStatus paymentStatus, PaymentMethod paymentMethod){
        if (userIdFilter != null) Validation.checkOwnership(userIdFilter, calledUserId);

        return repo.findByUserId(calledUserId).stream()
                .filter(p -> orderIdFilter == null || (p.getOrder() != null && orderIdFilter.equals(p.getOrder().getId())))
                .filter(p -> paymentStatus == null || paymentStatus == p.getStatus())
                .filter(p -> paymentMethod == null || paymentMethod == p.getPaymentMethod())
                .map(this::toResp)
                .toList();
    }

    public PaymentResp updateStatus(Long paymentId, Long calledUserId, UpdatePaymentStatusReq dto){
        Payment payment = getEntity(paymentId, calledUserId);
        payment.setStatus(dto.status());
        return toResp(repo.save(payment));
    }

    private Payment getEntity(Long paymentId, Long calledUserId){
        Payment payment = repo.findById(paymentId).orElseThrow(() -> new EntityNotFoundException("Payment", paymentId));
        Validation.checkOwnership(payment.getUser().getId(), calledUserId);
        return payment;
    }

    private PaymentResp toResp(Payment payment){
        return new PaymentResp(
                payment.getId(),
                payment.getValue(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUser().getId(),
                payment.getOrder().getId()
        );
    }
}
