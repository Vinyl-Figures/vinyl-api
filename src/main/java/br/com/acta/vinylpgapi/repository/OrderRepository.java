package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends RepositoryBase<Order> {
    List<Order> findByUserId(Long userId);
}
