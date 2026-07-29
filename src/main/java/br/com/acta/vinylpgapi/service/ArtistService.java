package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.dto.artist.ArtistResp;
import br.com.acta.vinylpgapi.dto.artist.CreateArtistReq;
import br.com.acta.vinylpgapi.dto.artist.UpdateArtistReq;
import br.com.acta.vinylpgapi.model.Artist;
import br.com.acta.vinylpgapi.repository.RepositoryBase;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import org.springframework.stereotype.Service;

@Service

public class ArtistService extends ServiceBase<CreateArtistReq, UpdateArtistReq, ArtistResp, Artist> {
    public ArtistService(RepositoryBase<Artist> repo) {
        super(repo);
    }

    @Override
    protected String entityName() {
        return "Artist";
    }

    @Override
    protected ArtistResp toResponse(Artist artist) {
        return new ArtistResp(artist.getId(), artist.getName(), artist.getDescription());
    }

    @Override
    protected Artist toEntity(CreateArtistReq dto) {
        return new Artist(dto.name(), dto.description());
    }

    @Override
    protected Artist updateEntity(Artist artist, UpdateArtistReq dto) {
        if (dto.name() != null) artist.setName(dto.name());
        if (dto.description() != null) artist.setDescription(dto.description());
        return artist;
    }
}
