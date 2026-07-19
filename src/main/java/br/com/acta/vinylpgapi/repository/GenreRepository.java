package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.Genre;

public interface GenreRepository extends RepositoryBase<Genre> {
    boolean existsByNameIgnoreCase(String name);
}
