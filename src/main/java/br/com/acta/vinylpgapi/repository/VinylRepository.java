package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.Vinyl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VinylRepository extends RepositoryBase<Vinyl> {
    @Query("SELECT v.vinyl FROM VinylGenre v WHERE v.genre.id = :genreId")
    List<Vinyl> findByGenreId(@Param("genreId") Long genreId);

    @Query("SELECT v.vinyl FROM VinylArtist v WHERE v.artist.id = :artistId")
    List<Vinyl> findByArtistId(@Param("artistId") Long artistId);
}
