package br.com.acta.vinylpgapi.model.join;

import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.model.Vinyl;
import br.com.acta.vinylpgapi.model.base.ModelBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "carts", uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "id_vinyl"}))
@AllArgsConstructor @NoArgsConstructor
public class Cart extends ModelBase {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_vinyl", nullable = false)
    private Vinyl vinyl;

}