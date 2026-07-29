package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.dto.accessibility.AccessibilityResp;
import br.com.acta.vinylpgapi.dto.accessibility.CreateAccessibilityReq;
import br.com.acta.vinylpgapi.dto.accessibility.UpdateAccessibilityReq;
import br.com.acta.vinylpgapi.model.Accessibility;
import br.com.acta.vinylpgapi.repository.AccessibilityRepository;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import org.springframework.stereotype.Service;

@Service
public class AccessibilityService
extends ServiceBase<CreateAccessibilityReq, UpdateAccessibilityReq, AccessibilityResp, Accessibility> {
    public AccessibilityService(AccessibilityRepository repo){
        super(repo);
    }

    @Override
    protected String entityName() {
        return "Accessibility";
    }

    @Override
    protected AccessibilityResp toResponse(Accessibility accessibility) {
        return new AccessibilityResp(accessibility.getId(), accessibility.getName(), accessibility.getDescription());
    }

    @Override
    protected Accessibility toEntity(CreateAccessibilityReq dto) {
        return new Accessibility(
                dto.name(),
                dto.description()
        );
    }

    @Override
    protected Accessibility updateEntity(Accessibility accessibility, UpdateAccessibilityReq dto) {
        if (dto.name() != null) accessibility.setName(dto.name());
        if (dto.description() != null) accessibility.setDescription(dto.description());
        return accessibility;
    }
}
