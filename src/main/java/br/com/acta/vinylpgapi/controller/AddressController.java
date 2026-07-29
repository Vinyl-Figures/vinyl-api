package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.addresses.AddressReq;
import br.com.acta.vinylpgapi.dto.addresses.AddressResp;
import br.com.acta.vinylpgapi.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;
    private final CurrentUser currentUser;

    @GetMapping("users/{userId}/addresses")
    public ResponseEntity<List<AddressResp>> getAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(service.listByUser(userId, currentUser.getUserId()));
    }

    @PostMapping("users/{userId}/addresses")
    public ResponseEntity<AddressResp> createAddress(@PathVariable Long userId, @RequestBody AddressReq dto) {
        return ResponseEntity.status(201).body(service.create(dto, userId));
    }

    @GetMapping("addresses/{addressId}")
    public ResponseEntity<AddressResp> getAddress(@PathVariable Long addressId) {
        return ResponseEntity.ok(service.getOwned(addressId, currentUser.getUserId()));
    }

    @PatchMapping("addresses/{addressId}")
    public ResponseEntity<AddressResp> patchAddress(@PathVariable Long addressId, @RequestBody AddressReq dto) {
        return ResponseEntity.ok(service.patchOwned(addressId, currentUser.getUserId(), dto));
    }

    @DeleteMapping("addresses/{addressId}")
    public ResponseEntity<Void> delete(@PathVariable Long addressId){
        service.deleteOwned(addressId, currentUser.getUserId());
        return ResponseEntity.noContent().build();
    }
}
