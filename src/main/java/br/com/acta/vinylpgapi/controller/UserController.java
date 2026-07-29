package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.user.CreateUserReq;
import br.com.acta.vinylpgapi.dto.user.UpdateUserReq;
import br.com.acta.vinylpgapi.dto.user.UserResp;
import br.com.acta.vinylpgapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final CurrentUser currentUser;

    @PostMapping
    public ResponseEntity<UserResp> create(@Valid @RequestBody CreateUserReq dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResp>> list(){
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResp> get(@PathVariable Long id){
        return ResponseEntity.ok(service.getUser(id, currentUser.getUserId()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResp> patch(@PathVariable Long id, @Valid @RequestBody UpdateUserReq dto){
        return ResponseEntity.ok(service.patchUser(id, currentUser.getUserId(), dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteUser(id, currentUser.getUserId());
        return ResponseEntity.noContent().build();
    }
}
