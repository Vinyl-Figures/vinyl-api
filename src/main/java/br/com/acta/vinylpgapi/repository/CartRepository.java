package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.join.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends RepositoryBase<Cart> {
    List<Cart> findByUserId(Long userId);
    boolean existsByUserIdAndVinylId(Long userId, Long vinylId);
    Optional<Cart> findByUserIdAndVinylId(Long userId, Long vinylId);
    void deleteByUserId(Long userId);
}
