package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.join.VinylArtist;

import java.util.List;

public interface VinylArtistRepository extends RepositoryBase<VinylArtist> {
    List<VinylArtist> findByVinylId(Long vinylId);
    boolean existsByVinylIdAndArtistId(Long vinylId, Long artistId);
    void deleteByVinylIdAndArtistId(Long vinylId, Long artistId);
}
