package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends RepositoryBase<Payment>{
    List<Payment> findByUserId(Long userId);
}
