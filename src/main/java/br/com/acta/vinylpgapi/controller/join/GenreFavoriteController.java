package br.com.acta.vinylpgapi.controller.join;

import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;
import br.com.acta.vinylpgapi.dto.genre.join.FavoriteGenreReq;
import br.com.acta.vinylpgapi.dto.genre.join.FavoriteGenreResp;
import br.com.acta.vinylpgapi.service.join.GenreFavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/favoriteGenres")
@RequiredArgsConstructor
public class GenreFavoriteController {
    private final GenreFavoriteService service;
    private final CurrentUser currentUser;

    @GetMapping
    public ResponseEntity<List<GenreResp>> list(@PathVariable Long userId) {
        return ResponseEntity.ok(service.list(userId, currentUser.getUserId()));
    }

    @PostMapping
    public ResponseEntity<FavoriteGenreResp> favorite(@PathVariable Long userId, @Valid @RequestBody FavoriteGenreReq dto){
        return ResponseEntity.status(201).body(service.favorite(userId, currentUser.getUserId(), dto));
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<Void> unfavorite(@PathVariable Long userId, @PathVariable Long genreId) {
        service.unfavorite(userId, currentUser.getUserId(), genreId);
        return ResponseEntity.noContent().build();
    }
}
