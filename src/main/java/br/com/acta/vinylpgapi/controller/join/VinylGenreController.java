package br.com.acta.vinylpgapi.controller.join;

import br.com.acta.vinylpgapi.controller.base.VinylJoinControllerBase;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylGenreJoinReq;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylGenreJoinResp;
import br.com.acta.vinylpgapi.service.base.JoinVinylBase;
import br.com.acta.vinylpgapi.service.join.VinylGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vinyls/{vinylId}/genres")
@RequiredArgsConstructor
public class VinylGenreController extends VinylJoinControllerBase<GenreResp, VinylGenreJoinReq, VinylGenreJoinResp> {
    private final VinylGenreService service;

    @Override
    protected JoinVinylBase<GenreResp, VinylGenreJoinReq, VinylGenreJoinResp> service() {
        return service;
    }
}
