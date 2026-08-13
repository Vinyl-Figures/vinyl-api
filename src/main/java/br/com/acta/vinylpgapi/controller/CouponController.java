package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.controller.base.ControllerBase;
import br.com.acta.vinylpgapi.dto.coupon.CouponResp;
import br.com.acta.vinylpgapi.dto.coupon.CreateCouponReq;
import br.com.acta.vinylpgapi.dto.coupon.UpdateCouponReq;
import br.com.acta.vinylpgapi.model.Coupon;
import br.com.acta.vinylpgapi.service.CouponService;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController extends ControllerBase<CreateCouponReq, UpdateCouponReq, CouponResp> {
    private final CouponService service;

    @Override
    protected ServiceBase<CreateCouponReq, UpdateCouponReq, CouponResp, ?> service() {
        return service;
    }

    // Pré-visualização do desconto pelo código, pro botão "Aplicar cupom"
    // do carrinho — sem precisar saber o id numérico do cupom.
    @GetMapping("/code/{code}")
    public ResponseEntity<CouponResp> getByCode(@PathVariable String code) {
        Coupon coupon = service.getEntityByCode(code);
        return ResponseEntity.ok(new CouponResp(coupon.getId(), coupon.getCode(), coupon.getDiscountPercent()));
    }
}
