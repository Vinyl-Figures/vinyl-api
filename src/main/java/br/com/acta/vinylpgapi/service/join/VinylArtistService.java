package br.com.acta.vinylpgapi.service.join;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.artist.ArtistResp;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylArtistJoinReq;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylArtistJoinResp;
import br.com.acta.vinylpgapi.model.Artist;
import br.com.acta.vinylpgapi.model.Vinyl;
import br.com.acta.vinylpgapi.model.join.VinylArtist;
import br.com.acta.vinylpgapi.repository.VinylArtistRepository;
import br.com.acta.vinylpgapi.repository.VinylRepository;
import br.com.acta.vinylpgapi.service.ArtistService;
import br.com.acta.vinylpgapi.service.base.JoinVinylBase;
import br.com.acta.vinylpgapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VinylArtistService implements JoinVinylBase<ArtistResp, VinylArtistJoinReq, VinylArtistJoinResp> {
    private final VinylArtistRepository repo;
    private final VinylRepository vinylRepository;
    private final ArtistService artistService;


    @Override
    public List<ArtistResp> list(Long vinylId) {
        getEntity(vinylId);
        return repo.findByVinylId(vinylId)
                .stream().map(v -> new ArtistResp(v.getArtist().getId(), v.getArtist().getName(), v.getArtist().getDescription()))
                .toList();
    }

    @Override
    public VinylArtistJoinResp associate(Long vinylId, VinylArtistJoinReq dto) {
        Vinyl vinyl = getEntity(vinylId);
        Artist artist = artistService.getEntity(dto.artistId());

        boolean exists = repo.existsByVinylIdAndArtistId(vinylId, dto.artistId());
        Validation.checkUniqueConstraint(exists, "Artist", "Vinyl");

        VinylArtist vinylArtist = new VinylArtist(vinyl, artist);
        repo.save(vinylArtist);

        return new VinylArtistJoinResp(vinylId, vinylArtist.getId());
    }

    @Transactional
    @Override
    public void remove(Long vinylId, Long id) {
        getEntity(vinylId);
        if (!repo.existsByVinylIdAndArtistId(vinylId, id)) throw new EntityNotFoundException("VinylArtist", id);
        repo.deleteByVinylIdAndArtistId(vinylId, id);
    }

    @Override
    public Vinyl getEntity(Long vinylId) {
        return vinylRepository.findById(vinylId).orElseThrow(() -> new EntityNotFoundException("Vinyl", vinylId));
    }
}
