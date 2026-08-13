package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.common.exceptions.ValidationException;
import br.com.acta.vinylpgapi.dto.order.OrderItemResp;
import br.com.acta.vinylpgapi.dto.order.OrderReq;
import br.com.acta.vinylpgapi.dto.order.OrderResp;
import br.com.acta.vinylpgapi.dto.shipping.ShippingResp;
import br.com.acta.vinylpgapi.model.Coupon;
import br.com.acta.vinylpgapi.model.Order;
import br.com.acta.vinylpgapi.model.join.Cart;
import br.com.acta.vinylpgapi.model.join.OrderItem;
import br.com.acta.vinylpgapi.repository.CartRepository;
import br.com.acta.vinylpgapi.repository.OrderItemRepository;
import br.com.acta.vinylpgapi.repository.OrderRepository;
import br.com.acta.vinylpgapi.service.join.CartService;
import br.com.acta.vinylpgapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final ShippingService shippingService;
    private final CouponService couponService;

    @Transactional
    public OrderResp checkout(OrderReq dto, Long calledUser){
        Validation.checkOwnership(dto.userId(), calledUser);

        List<Cart> cartItems = cartRepository.findByUserId(dto.userId());
        if (cartItems.isEmpty()) throw new ValidationException("Cart is empty");

        BigDecimal subtotal = cartItems.stream()
                .map(c -> c.getVinyl().getPrice().multiply(BigDecimal.valueOf(c.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal shippingPrice = BigDecimal.ZERO;
        if (dto.zipCode() != null) {
            ShippingResp shipping = shippingService.calculate(dto.zipCode());
            shippingPrice = shipping.price();
        }

        String couponCode = null;
        BigDecimal total = subtotal.add(shippingPrice);
        if (dto.couponCode() != null) {
            Coupon coupon = couponService.getEntityByCode(dto.couponCode());
            BigDecimal fator = BigDecimal.ONE.subtract(
                    coupon.getDiscountPercent().divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
            );
            total = total.multiply(fator).setScale(2, RoundingMode.HALF_UP);
            couponCode = coupon.getCode();
        }

        Order order = new Order(
                cartItems.getFirst().getUser(),
                total,
                shippingPrice,
                couponCode
        );

        Order saved = repo.save(order);

        List<OrderItem> orderItems = cartItems.stream().map(c -> {
            OrderItem orderItem = new OrderItem(
                    saved,
                    c.getVinyl(),
                    c.getVinyl().getPrice(),
                    c.getQuantity()
            );

            return orderItemRepository.save(orderItem);
        }).toList();

        cartRepository.deleteByUserId(dto.userId());
        return toResp(saved, orderItems);
    }

    public List<OrderResp> listByUser(Long userId, Long callerUserId){
        Validation.checkOwnership(userId, callerUserId);
        return repo.findByUserId(userId).stream().map(o -> toResp(o, orderItemRepository.findByOrderId(o.getId()))).toList();
    }

    public OrderResp getById(Long orderId, Long calledUserId, boolean expandItems){
        Order order = getEntity(orderId, calledUserId);
        List<OrderItem> items = expandItems ? orderItemRepository.findByOrderId(orderId) : null;
        return toResp(order, items);
    }

    public void delete(Long orderId, Long calledUserId){
        Order order = getEntity(orderId, calledUserId);
        repo.delete(order);
    }

    public List<OrderItemResp>listItems(Long orderId, Long calledUserId){
        getEntity(orderId, calledUserId);
        return orderItemRepository.findByOrderId(orderId).stream().map(this::toItemResp).toList();
    }

    public Order getEntity(Long orderId, Long calledUserId){
        Order order = repo.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order", orderId));
        Validation.checkOwnership(order.getUser().getId(), calledUserId);

        return order;
    }

    private OrderResp toResp(Order order, List<OrderItem> items) {
        List<OrderItemResp> itemResps = items.stream().map(this::toItemResp).toList();

        return new OrderResp(
                order.getId(),
                order.getUser().getId(),
                order.getTotalPrice(),
                order.getShippingPrice(),
                order.getCouponCode(),
                order.getCreatedAt(),
                itemResps
        );
    }

    private OrderItemResp toItemResp(OrderItem orderItem) {
        return new OrderItemResp(
                orderItem.getId(),
                orderItem.getVinyl().getId(),
                orderItem.getPriceAtPurchase(),
                orderItem.getQuantity()
        );
    }
}
