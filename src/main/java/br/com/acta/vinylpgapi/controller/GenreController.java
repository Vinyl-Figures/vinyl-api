package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.controller.base.ControllerBase;
import br.com.acta.vinylpgapi.dto.genre.GenreReq;
import br.com.acta.vinylpgapi.dto.genre.GenreResp;
import br.com.acta.vinylpgapi.service.GenreService;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController extends ControllerBase<GenreReq, GenreReq, GenreResp>{
    private final GenreService service;

    @Override
    protected ServiceBase<GenreReq, GenreReq, GenreResp, ?> service() {
        return service;
    }
}
