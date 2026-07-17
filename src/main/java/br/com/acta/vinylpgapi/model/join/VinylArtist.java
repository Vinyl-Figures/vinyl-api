package br.com.acta.vinylpgapi.model.join;

import br.com.acta.vinylpgapi.model.Artist;
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
@Table(name = "vinyl_artists", uniqueConstraints = @UniqueConstraint(columnNames = {"id_vinyl", "id_artist"}))
@AllArgsConstructor @NoArgsConstructor
public class VinylArtist extends ModelBase {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_vinyl", nullable = false)
    private Vinyl vinyl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_artist", nullable = false)
    private Artist artist;


}