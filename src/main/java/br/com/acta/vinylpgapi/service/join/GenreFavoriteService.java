package br.com.acta.vinylpgapi.service.join;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;
import br.com.acta.vinylpgapi.dto.genre.join.FavoriteGenreReq;
import br.com.acta.vinylpgapi.dto.genre.join.FavoriteGenreResp;
import br.com.acta.vinylpgapi.model.Genre;
import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.model.join.GenreFavorite;
import br.com.acta.vinylpgapi.repository.GenreFavoriteRepository;
import br.com.acta.vinylpgapi.service.GenreService;
import br.com.acta.vinylpgapi.service.UserService;
import br.com.acta.vinylpgapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreFavoriteService {
    private final GenreFavoriteRepository repo;
    private final UserService userService;
    private final GenreService genreService;

    public List<GenreResp> list(Long userId, Long calledUserId){
        Validation.checkOwnership(userId, calledUserId);

        return repo.findByUserId(userId)
                .stream().map(g -> new GenreResp(g.getGenre().getId(), g.getGenre().getName()))
                .toList();
    }

    public FavoriteGenreResp favorite(Long userId, Long callerUserId, FavoriteGenreReq dto){
        Validation.checkOwnership(userId, callerUserId);

        User user = userService.getEntity(userId);
        Genre genre = genreService.getEntity(dto.genreId());

        boolean exists = repo.existsByUserIdAndGenreId(userId, dto.genreId());
        Validation.checkUniqueConstraint(exists, "Genre", "User");

        repo.save(new GenreFavorite(user, genre));
        return new FavoriteGenreResp(userId, genre.getId());
    }

    @Transactional
    public void unfavorite(Long userId, Long callerUserId, Long genreId){
        Validation.checkOwnership(userId, callerUserId);

        boolean exists = repo.existsByUserIdAndGenreId(userId, genreId);
        if (!exists) throw new EntityNotFoundException("This user has not favorited genre " + genreId);

        repo.deleteByUserIdAndGenreId(userId, genreId);
    }
}
