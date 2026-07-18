package br.com.acta.vinylpgapi.model;

import br.com.acta.vinylpgapi.model.base.ModelBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "genres")
@AllArgsConstructor @NoArgsConstructor
public class Genre extends ModelBase {
    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;
}