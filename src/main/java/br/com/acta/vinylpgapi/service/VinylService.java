package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.dto.artist.ArtistResp;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;
import br.com.acta.vinylpgapi.dto.vinyl.CreateVinylReq;
import br.com.acta.vinylpgapi.dto.vinyl.UpdateVinylReq;
import br.com.acta.vinylpgapi.dto.vinyl.VinylResp;
import br.com.acta.vinylpgapi.model.Vinyl;
import br.com.acta.vinylpgapi.repository.VinylArtistRepository;
import br.com.acta.vinylpgapi.repository.VinylGenreRepository;
import br.com.acta.vinylpgapi.repository.VinylRepository;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinylService
extends ServiceBase<CreateVinylReq, UpdateVinylReq, VinylResp, Vinyl> {
    private final VinylRepository repo;
    private final VinylGenreRepository vinylGenreRepository;
    private final VinylArtistRepository vinylArtistRepository;

    public VinylService(VinylRepository repo, VinylRepository repo1, VinylGenreRepository vinylGenreRepository, VinylArtistRepository vinylArtistRepository) {
        super(repo);
        this.repo = repo1;
        this.vinylGenreRepository = vinylGenreRepository;
        this.vinylArtistRepository = vinylArtistRepository;
    }


    @Override
    protected String entityName() {
        return "Vinyl";
    }

    @Override
    protected VinylResp toResponse(Vinyl vinyl) {
        return new VinylResp(vinyl.getId(), vinyl.getTitle(), vinyl.getPrice(), vinyl.getDescription(), vinyl.getReleasedAt(), vinyl.getImageUrl());
    }

    @Override
    protected Vinyl toEntity(CreateVinylReq dto) {
        return new Vinyl(
                dto.title(),
                dto.price(),
                dto.description(),
                dto.releasedAt(),
                dto.imageUrl()
        );
    }

    @Override
    protected Vinyl updateEntity(Vinyl vinyl, UpdateVinylReq dto) {
        if (dto.title() != null) vinyl.setTitle(dto.title());
        if (dto.price() != null) vinyl.setPrice(dto.price());
        if (dto.description() != null) vinyl.setDescription(dto.description());
        if (dto.releasedAt() != null) vinyl.setReleasedAt(dto.releasedAt());
        if (dto.imageUrl() != null) vinyl.setImageUrl(dto.imageUrl());
        return vinyl;
    }

    public List<VinylResp> listVinyls(Long genreId, Long artistId) {
        List<Vinyl> vinyls;

        if (genreId != null) vinyls = repo.findByGenreId(genreId);
        else if (artistId != null) vinyls = repo.findByArtistId(artistId);
        else vinyls = repo.findAll();

        return vinyls.stream().map(this::toResponse).toList();
    }

    public VinylResp getById(Long id, boolean expandGenres, boolean expandArtists) {
        Vinyl vinyl = getEntity(id);
        List<GenreResp> genres = null;
        List<ArtistResp> artists = null;

        if (expandGenres) {
            genres = vinylGenreRepository.findByVinylId(id)
                    .stream().map(v -> new GenreResp(v.getGenre().getId(), v.getGenre().getName()))
                    .toList();
        }

        if (expandArtists) {
            artists = vinylArtistRepository.findByVinylId(id)
                    .stream().map(v -> new ArtistResp(v.getArtist().getId(), v.getArtist().getName(), v.getArtist().getDescription()))
                    .toList();
        }

        return new VinylResp(vinyl.getId(), vinyl.getTitle(), vinyl.getPrice(), vinyl.getDescription(), vinyl.getReleasedAt(), vinyl.getImageUrl(), genres, artists);
    }
}
