package br.com.acta.vinylpgapi.model;

import br.com.acta.vinylpgapi.model.base.NameDescriptionBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "artists")
@AttributeOverride(name = "name", column = @Column(nullable = false, length = 60))
public class Artist extends NameDescriptionBase {
    public Artist(String name, String description) {
        super(name, description);
    }

    public Artist() {
    }
}