package br.com.acta.vinylpgapi.service.join;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.cart.AddCartItemReq;
import br.com.acta.vinylpgapi.dto.cart.AddCartItemResp;
import br.com.acta.vinylpgapi.dto.cart.CartItemResp;
import br.com.acta.vinylpgapi.dto.cart.UpdateCartItemReq;
import br.com.acta.vinylpgapi.dto.vinyl.VinylSummary;
import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.model.Vinyl;
import br.com.acta.vinylpgapi.model.join.Cart;
import br.com.acta.vinylpgapi.repository.CartRepository;
import br.com.acta.vinylpgapi.service.UserService;
import br.com.acta.vinylpgapi.service.VinylService;
import br.com.acta.vinylpgapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repo;
    private final UserService userService;
    private final VinylService vinylService;

    public List<CartItemResp> list(Long userId, Long calledUserId, boolean expandVinyl){
        Validation.checkOwnership(userId, calledUserId);

        return repo.findByUserId(userId).stream()
                .map(c -> toResp(c, expandVinyl))
                .toList();
    }

    @Transactional
    public AddCartItemResp addCartItemResp(Long userId, Long callerUserId, AddCartItemReq dto){
        Validation.checkOwnership(userId, callerUserId);

        List<CartItemResp> created = new ArrayList<>();
        Map<Long, String> skipped = new LinkedHashMap<>();

        for (Long vinylId : dto.vinylIds()){
            Vinyl vinyl = vinylService.getEntity(vinylId);
            User user = userService.getEntity(userId);

            // Já no carrinho: soma quantidade em vez de pular.
            Cart cart = repo.findByUserIdAndVinylId(userId, vinylId)
                    .map(existente -> {
                        existente.setQuantity(existente.getQuantity() + 1);
                        return existente;
                    })
                    .orElseGet(() -> new Cart(user, vinyl, 1));

            Cart saved = repo.save(cart);

            created.add(new CartItemResp(saved.getId(), userId, vinylId, saved.getQuantity(), null));
        }

        return new AddCartItemResp(created, skipped);
    }

    @Transactional
    public CartItemResp updateQuantity(Long userId, Long calledUserId, Long vinylId, UpdateCartItemReq dto){
        Validation.checkOwnership(userId, calledUserId);

        Cart cart = repo.findByUserIdAndVinylId(userId, vinylId)
                .orElseThrow(() -> new EntityNotFoundException("The Item in cart with User ID " + userId + " and Vinyl ID " + vinylId + " not found"));

        cart.setQuantity(dto.quantity());
        Cart saved = repo.save(cart);

        return toResp(saved, false);
    }

    public void remove(Long userId, Long calledUserId, Long vinylId){
        Validation.checkOwnership(userId, calledUserId);

        Cart cart = repo.findByUserIdAndVinylId(userId, vinylId)
                .orElseThrow(() -> new EntityNotFoundException("The Item in cart with User ID " + userId + " and Vinyl ID " + vinylId + " not found"));
        repo.delete(cart);
    }

    public void clear(Long userId, Long calledUserId){
        Validation.checkOwnership(userId, calledUserId);
        repo.deleteByUserId(userId);
    }

    private CartItemResp toResp(Cart cart, boolean expandVinyl){
        VinylSummary summary = null;

        if (expandVinyl) {
            Vinyl vinyl = cart.getVinyl();
            summary = new VinylSummary(vinyl.getId(), vinyl.getTitle(), vinyl.getPrice());
        }

        return new CartItemResp(cart.getId(), cart.getUser().getId(), cart.getVinyl().getId(), cart.getQuantity(), summary);
    }
}
