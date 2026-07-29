package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.join.VinylGenre;

import java.util.List;

public interface VinylGenreRepository extends RepositoryBase<VinylGenre>{
    List<VinylGenre> findByVinylId(Long vinylId);
    boolean existsByVinylIdAndGenreId(Long vinylId, Long genreId);
    void deleteByVinylIdAndGenreId(Long vinylId, Long genreId);
}
