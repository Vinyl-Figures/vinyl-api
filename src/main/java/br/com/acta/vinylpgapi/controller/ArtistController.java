package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.controller.base.ControllerBase;
import br.com.acta.vinylpgapi.dto.artist.ArtistResp;
import br.com.acta.vinylpgapi.dto.artist.CreateArtistReq;
import br.com.acta.vinylpgapi.dto.artist.UpdateArtistReq;
import br.com.acta.vinylpgapi.service.ArtistService;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController extends ControllerBase<CreateArtistReq, UpdateArtistReq, ArtistResp> {
    private final ArtistService service;

    @Override
    protected ServiceBase<CreateArtistReq, UpdateArtistReq, ArtistResp, ?> service() {
        return service;
    }
}
