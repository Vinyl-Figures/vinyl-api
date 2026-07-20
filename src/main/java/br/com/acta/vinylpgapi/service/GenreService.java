package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.ConflictException;
import br.com.acta.vinylpgapi.dto.genre.GenreReq;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;
import br.com.acta.vinylpgapi.model.Genre;
import br.com.acta.vinylpgapi.repository.GenreRepository;
import br.com.acta.vinylpgapi.repository.RepositoryBase;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import org.springframework.stereotype.Service;

@Service
public class GenreService extends ServiceBase<GenreReq, GenreReq, GenreResp, Genre> {
    private final GenreRepository repo;

    public GenreService(RepositoryBase<Genre> repo, GenreRepository repo1) {
        super(repo);
        this.repo = repo1;
    }


    @Override
    protected String entityName() {
        return "Genre";
    }

    @Override
    protected GenreResp toResponse(Genre genre) {
        return new GenreResp(genre.getId(), genre.getName());
    }

    @Override
    protected Genre toEntity(GenreReq dto) {
        return new Genre(dto.name());
    }

    @Override
    protected Genre updateEntity(Genre genre, GenreReq dto) {
        genre.setName(dto.name());
        return genre;
    }

    @Override
    public GenreResp create(GenreReq dto) {
        if (repo.existsByNameIgnoreCase(dto.name())) throw new ConflictException("Genre name");
        return super.create(dto);
    }

    @Override
    public GenreResp patch(Long id, GenreReq dto) {
        if (repo.existsByNameIgnoreCase(dto.name())) throw new ConflictException("Genre name");
        return super.patch(id, dto);
    }
}
