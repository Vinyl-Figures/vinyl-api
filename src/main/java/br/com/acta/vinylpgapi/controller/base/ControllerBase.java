package br.com.acta.vinylpgapi.controller.base;

import br.com.acta.vinylpgapi.service.base.ServiceBase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class ControllerBase<CREATE_REQ, UPDATE_REQ, RESP> {
    protected abstract ServiceBase<CREATE_REQ, UPDATE_REQ, RESP, ?> service();

    @GetMapping
    public List<RESP> list(){
        return service().listAll();
    }

    @PostMapping
    public ResponseEntity<RESP> create(@Valid @RequestBody CREATE_REQ dto){
        return ResponseEntity.status(201).body(service().create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RESP> get(@PathVariable Long id){
        return ResponseEntity.ok(service().getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RESP> update(@PathVariable Long id, @Valid @RequestBody UPDATE_REQ dto){
        return ResponseEntity.ok(service().patch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service().deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
