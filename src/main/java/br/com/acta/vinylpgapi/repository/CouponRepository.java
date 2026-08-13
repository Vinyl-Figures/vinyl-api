package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.Coupon;

import java.util.Optional;

public interface CouponRepository extends RepositoryBase<Coupon> {
    Optional<Coupon> findByCode(String code);
}
