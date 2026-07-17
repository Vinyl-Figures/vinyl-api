package br.com.acta.vinylpgapi.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@MappedSuperclass
@Setter
@AllArgsConstructor @NoArgsConstructor
public class NameDescriptionBase extends ModelBase {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
