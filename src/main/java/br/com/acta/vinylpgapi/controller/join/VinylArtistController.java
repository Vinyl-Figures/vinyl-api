package br.com.acta.vinylpgapi.controller.join;

import br.com.acta.vinylpgapi.controller.base.VinylJoinControllerBase;
import br.com.acta.vinylpgapi.dto.artist.ArtistResp;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylArtistJoinReq;
import br.com.acta.vinylpgapi.dto.vinyl.join.VinylArtistJoinResp;
import br.com.acta.vinylpgapi.service.base.JoinVinylBase;
import br.com.acta.vinylpgapi.service.join.VinylArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vinyls/{vinylId}/artists")
@RequiredArgsConstructor
public class VinylArtistController extends VinylJoinControllerBase<ArtistResp, VinylArtistJoinReq, VinylArtistJoinResp> {
    private final VinylArtistService service;

    @Override
    protected JoinVinylBase<ArtistResp, VinylArtistJoinReq, VinylArtistJoinResp> service() {
        return service;
    }
}
