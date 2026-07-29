package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.join.OrderItem;

import java.util.List;

public interface OrderItemRepository extends RepositoryBase<OrderItem> {
    List<OrderItem> findByOrderId(Long orderId);
}
