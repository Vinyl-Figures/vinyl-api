package br.com.acta.vinylpgapi.model;

import br.com.acta.vinylpgapi.model.base.ModelBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "coupons")
@AllArgsConstructor @NoArgsConstructor
public class Coupon extends ModelBase {
    @Column(name = "code", nullable = false, unique = true, length = 30)
    private String code;

    @Column(name = "discount_percent", nullable = false, precision = 5, scale = 2)
    private BigDecimal discountPercent;
}
