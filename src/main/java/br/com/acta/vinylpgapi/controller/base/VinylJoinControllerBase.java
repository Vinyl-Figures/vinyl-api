package br.com.acta.vinylpgapi.controller.base;

import br.com.acta.vinylpgapi.service.base.JoinVinylBase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class VinylJoinControllerBase<RESP, JOIN_REQ, JOIN_RESP> {
    protected abstract JoinVinylBase<RESP, JOIN_REQ, JOIN_RESP> service();

    @GetMapping
    public List<RESP> list(@PathVariable Long vinylId){
        return service().list(vinylId);
    }

    @PostMapping
    public ResponseEntity<JOIN_RESP> create(@PathVariable Long vinylId, @Valid @RequestBody JOIN_REQ dto){
        return ResponseEntity.status(201).body(service().associate(vinylId, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long vinylId, @PathVariable Long id){
        service().remove(vinylId, id);
        return ResponseEntity.noContent().build();
    }
}
