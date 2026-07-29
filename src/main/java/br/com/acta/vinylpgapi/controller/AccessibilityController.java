package br.com.acta.vinylpgapi.controller;

import br.com.acta.vinylpgapi.controller.base.ControllerBase;
import br.com.acta.vinylpgapi.dto.accessibility.AccessibilityResp;
import br.com.acta.vinylpgapi.dto.accessibility.CreateAccessibilityReq;
import br.com.acta.vinylpgapi.dto.accessibility.UpdateAccessibilityReq;
import br.com.acta.vinylpgapi.service.AccessibilityService;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accessibility")
@RequiredArgsConstructor
public class AccessibilityController extends ControllerBase<CreateAccessibilityReq, UpdateAccessibilityReq, AccessibilityResp> {
    private final AccessibilityService service;

    @Override
    protected ServiceBase<CreateAccessibilityReq, UpdateAccessibilityReq, AccessibilityResp, ?> service() {
        return service;
    }
}
