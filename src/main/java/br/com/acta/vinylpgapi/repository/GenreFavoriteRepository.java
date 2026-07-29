package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.join.GenreFavorite;

import java.util.List;

public interface GenreFavoriteRepository extends RepositoryBase<GenreFavorite> {
    List<GenreFavorite> findByUserId(Long userId);
    boolean existsByUserIdAndGenreId(Long userId, Long genreId);
    void deleteByUserIdAndGenreId(Long userId, Long genreId);
}
