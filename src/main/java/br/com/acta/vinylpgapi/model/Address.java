package br.com.acta.vinylpgapi.model;

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
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
public class Address extends ModelBase {
    @Column(name = "number", nullable = false, length = 12)
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public Address(String number, String complement, String zipCode) {
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
    }
}