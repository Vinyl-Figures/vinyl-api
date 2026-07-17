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
@Table(name = "vinyls")
@AllArgsConstructor @NoArgsConstructor
public class Vinyl extends ModelBase {
    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "released_at", nullable = false, length = 4)
    private String releasedAt;

    @Column(name = "image_url", nullable = false, length = Integer.MAX_VALUE)
    private String imageUrl;
}