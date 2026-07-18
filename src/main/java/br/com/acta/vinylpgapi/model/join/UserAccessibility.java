package br.com.acta.vinylpgapi.model.join;

import br.com.acta.vinylpgapi.model.Accessibility;
import br.com.acta.vinylpgapi.model.User;
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
@Table(name = "user_accessibility", uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "id_accessibility"}))
@AllArgsConstructor @NoArgsConstructor
public class UserAccessibility extends ModelBase {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_accessibility", nullable = false)
    private Accessibility accessibility;
}