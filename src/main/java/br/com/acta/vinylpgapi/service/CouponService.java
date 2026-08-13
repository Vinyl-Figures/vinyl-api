package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.coupon.CouponResp;
import br.com.acta.vinylpgapi.dto.coupon.CreateCouponReq;
import br.com.acta.vinylpgapi.dto.coupon.UpdateCouponReq;
import br.com.acta.vinylpgapi.model.Coupon;
import br.com.acta.vinylpgapi.repository.CouponRepository;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import org.springframework.stereotype.Service;

@Service
public class CouponService extends ServiceBase<CreateCouponReq, UpdateCouponReq, CouponResp, Coupon> {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        super(couponRepository);
        this.couponRepository = couponRepository;
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
}
