package br.com.acta.vinylpgapi.controller.join;

import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.cart.AddCartItemReq;
import br.com.acta.vinylpgapi.dto.cart.AddCartItemResp;
import br.com.acta.vinylpgapi.dto.cart.CartItemResp;
import br.com.acta.vinylpgapi.service.join.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/cartItems")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;
    private final CurrentUser currentUser;

    @GetMapping
    public ResponseEntity<List<CartItemResp>> listResponseEntity(@PathVariable Long userId, @RequestParam(required = false) String expand) {
        return ResponseEntity.ok(service.list(userId, currentUser.getUserId(), "vinyl".equalsIgnoreCase(expand)));
    }

    @PostMapping("/bulk")
    public ResponseEntity<AddCartItemResp> bulkAdd(@PathVariable Long userId, @Valid @RequestBody AddCartItemReq dto){
        return ResponseEntity.status(201).body(service.addCartItemResp(userId, currentUser.getUserId(), dto));
    }

    @DeleteMapping("/{vinylId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long vinylId){
        service.remove(userId, currentUser.getUserId(), vinylId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clear(@PathVariable Long userId){
        service.clear(userId, currentUser.getUserId());
        return ResponseEntity.noContent().build();
    }

}
