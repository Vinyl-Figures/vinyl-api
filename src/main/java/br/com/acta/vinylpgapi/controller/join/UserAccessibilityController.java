package br.com.acta.vinylpgapi.controller.join;


import br.com.acta.vinylpgapi.common.security.CurrentUser;
import br.com.acta.vinylpgapi.dto.accessibility.AccessibilityResp;
import br.com.acta.vinylpgapi.dto.accessibility.join.UserAccessibilityReq;
import br.com.acta.vinylpgapi.dto.accessibility.join.UserAccessibilityResp;
import br.com.acta.vinylpgapi.service.join.UserAccessibilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/accessibility")
@RequiredArgsConstructor
public class UserAccessibilityController {
    private final UserAccessibilityService service;
    private final CurrentUser currentUser;

    @GetMapping
    public ResponseEntity<List<AccessibilityResp>> listResponseEntity(@PathVariable Long userId) {
        List<AccessibilityResp> responses = service.list(userId, currentUser.getUserId());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<UserAccessibilityResp> select(@PathVariable Long userId, @Valid @RequestBody UserAccessibilityReq dto){
        return ResponseEntity.ok(service.select(userId, currentUser.getUserId(), dto));
    }

    @DeleteMapping("/{accessibilityId}")
    public ResponseEntity<Void> remove(@PathVariable Long userId, @PathVariable Long accessibilityId){
        service.remove(userId, currentUser.getUserId(), accessibilityId);
        return ResponseEntity.noContent().build();
    }
}
