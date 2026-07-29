package br.com.acta.vinylpgapi.service.join;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylGenreJoinReq;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylGenreJoinResp;
import br.com.acta.vinylpgapi.model.Genre;
import br.com.acta.vinylpgapi.model.Vinyl;
import br.com.acta.vinylpgapi.model.join.VinylGenre;
import br.com.acta.vinylpgapi.repository.VinylGenreRepository;
import br.com.acta.vinylpgapi.repository.VinylRepository;
import br.com.acta.vinylpgapi.service.GenreService;
import br.com.acta.vinylpgapi.service.base.JoinVinylBase;
import br.com.acta.vinylpgapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VinylGenreService implements JoinVinylBase<GenreResp, VinylGenreJoinReq, VinylGenreJoinResp> {
    private final VinylGenreRepository repo;
    private final VinylRepository vinylRepository;
    private final GenreService genreService;


    @Override
    public List<GenreResp> list(Long vinylId) {
        getEntity(vinylId);
        return repo.findByVinylId(vinylId).stream()
                .map(v -> new GenreResp(v.getGenre().getId(), v.getGenre().getName()))
                .toList();
    }

    @Override
    public VinylGenreJoinResp associate(Long vinylId, VinylGenreJoinReq dto) {
        Vinyl vinyl = getEntity(vinylId);
        Genre genre = genreService.getEntity(dto.genreId());

        boolean exists = repo.existsByVinylIdAndGenreId(vinylId, dto.genreId());
        Validation.checkUniqueConstraint(exists, "Genre", "Vinyl");

        repo.save(new VinylGenre(vinyl, genre));
        return new VinylGenreJoinResp(vinyl.getId(), genre.getId());
    }

    @Transactional
    @Override
    public void remove(Long vinylId, Long id) {
        getEntity(vinylId);

        if (!repo.existsByVinylIdAndGenreId(vinylId, id)) throw new EntityNotFoundException("This vinyl is not associated with genre " + id);
        repo.deleteByVinylIdAndGenreId(vinylId, id);
    }

    @Override
    public Vinyl getEntity(Long vinylId) {
        return vinylRepository.findById(vinylId).orElseThrow(() -> new EntityNotFoundException("Vinyl not found"));
    }
}
