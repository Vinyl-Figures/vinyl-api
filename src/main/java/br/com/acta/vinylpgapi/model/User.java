package br.com.acta.vinylpgapi.model;

import br.com.acta.vinylpgapi.model.base.ModelBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor @NoArgsConstructor
public class User extends ModelBase {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false, length = 11)
    private String document;

    @Column(name = "cellphone", nullable = false, length = 20)
    private String cellphone;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}