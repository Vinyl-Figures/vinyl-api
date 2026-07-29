package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.dto.vinyl.CreateVinylReq;
import br.com.acta.vinylpgapi.dto.vinyl.UpdateVinylReq;
import br.com.acta.vinylpgapi.dto.vinyl.VinylResp;
import br.com.acta.vinylpgapi.service.VinylService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vinyls")
@RequiredArgsConstructor
public class VinylController {
    private final VinylService service;

    @GetMapping
    public ResponseEntity<List<VinylResp>> list(@RequestParam(required = false) Long genreId, @RequestParam(required = false) Long artistId){
        return ResponseEntity.ok(service.listVinyls(genreId, artistId));
    }

    @PostMapping
    public ResponseEntity<VinylResp> create(@Valid @RequestBody CreateVinylReq dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VinylResp> get(@PathVariable Long id, @RequestParam(required = false) String expand){
        boolean expandGenres = expand != null && expand.contains("genres");
        boolean expandArtists = expand != null && expand.contains("artists");
        return ResponseEntity.ok(service.getById(id, expandGenres, expandArtists));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VinylResp>patch(@PathVariable Long id, @Valid @RequestBody UpdateVinylReq dto){
        return ResponseEntity.ok( service.patch(id, dto));
    }
}
