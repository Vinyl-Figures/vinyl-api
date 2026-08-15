package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.ConflictException;
import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.coupon.CouponResp;
import br.com.acta.vinylpgapi.dto.coupon.CreateCouponReq;
import br.com.acta.vinylpgapi.dto.coupon.UpdateCouponReq;
import br.com.acta.vinylpgapi.model.Coupon;
import br.com.acta.vinylpgapi.repository.CouponRepository;
import br.com.acta.vinylpgapi.repository.OrderRepository;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import org.springframework.stereotype.Service;

@Service
public class CouponService extends ServiceBase<CreateCouponReq, UpdateCouponReq, CouponResp, Coupon> {
    private final CouponRepository couponRepository;
    private final OrderRepository orderRepository;

    public CouponService(CouponRepository couponRepository, OrderRepository orderRepository) {
        super(couponRepository);
        this.couponRepository = couponRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    protected String entityName() {
        return "Coupon";
    }

    @Override
    protected CouponResp toResponse(Coupon coupon) {
        return new CouponResp(coupon.getId(), coupon.getCode(), coupon.getDiscountPercent());
    }

    @Override
    protected Coupon toEntity(CreateCouponReq dto) {
        return new Coupon(dto.code(), dto.discountPercent());
    }

    @Override
    protected Coupon updateEntity(Coupon coupon, UpdateCouponReq dto) {
        if (dto.code() != null) coupon.setCode(dto.code());
        if (dto.discountPercent() != null) coupon.setDiscountPercent(dto.discountPercent());
        return coupon;
    }

    public Coupon getEntityByCode(String code) {
        return couponRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("The Coupon with code " + code + " was not found"));
    }

    // Usado na pré-visualização do "Aplicar cupom" (carrinho), pra avisar
    // sobre cupom já usado antes de chegar no checkout — mesma regra que
    // OrderService.checkout() aplica de novo na hora de finalizar.
    public Coupon getEntityByCodeForUser(String code, Long userId) {
        Coupon coupon = getEntityByCode(code);

        if (orderRepository.existsByUserIdAndCouponCode(userId, coupon.getCode())) {
            throw new ConflictException("Coupon " + coupon.getCode(), "User " + userId);
        }

        return coupon;
    }
}
