package br.com.acta.vinylpgapi.model.join;

import br.com.acta.vinylpgapi.model.Genre;
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
@Table(name = "vinyl_genres", uniqueConstraints = @UniqueConstraint(columnNames = {"id_vinyl", "id_genre"}))
@AllArgsConstructor @NoArgsConstructor
public class VinylGenre extends ModelBase {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_vinyl", nullable = false)
    private Vinyl vinyl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_genre", nullable = false)
    private Genre genre;
}