package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.order.OrderItemResp;
import br.com.acta.vinylpgapi.dto.order.OrderReq;
import br.com.acta.vinylpgapi.dto.order.OrderResp;
import br.com.acta.vinylpgapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final CurrentUser currentUser;

    @GetMapping("users/{userId}/orders")
    public ResponseEntity<List<OrderResp>> listByUser(@PathVariable Long userId){
        return ResponseEntity.ok(service.listByUser(userId, currentUser.getUserId()));
    }

    @PostMapping("orders")
    public ResponseEntity<OrderResp> checkout(@RequestBody OrderReq request){
        return ResponseEntity.status(201).body(service.checkout(request, currentUser.getUserId()));
    }

    @GetMapping("orders/{orderId}")
    public ResponseEntity<OrderResp> get(@PathVariable Long orderId, @RequestParam(required = false) String expand){
        boolean expandItems = expand == null || expand.contains("items");
        return ResponseEntity.ok(service.getById(orderId, currentUser.getUserId(), expandItems));
    }

    @DeleteMapping("orders/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable Long orderId){
        service.delete(orderId, currentUser.getUserId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("orders/{orderId}/items")
    public ResponseEntity<List<OrderItemResp>> items(@PathVariable Long orderId){
        return ResponseEntity.ok(service.listItems(orderId, currentUser.getUserId()));
    }
}
