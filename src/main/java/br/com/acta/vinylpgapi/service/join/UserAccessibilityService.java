package br.com.acta.vinylpgapi.service.join;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.dto.accessibility.AccessibilityResp;
import br.com.acta.vinylpgapi.dto.accessibility.join.UserAccessibilityReq;
import br.com.acta.vinylpgapi.dto.accessibility.join.UserAccessibilityResp;
import br.com.acta.vinylpgapi.model.Accessibility;
import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.model.join.UserAccessibility;
import br.com.acta.vinylpgapi.repository.UserAccessibilityRepository;
import br.com.acta.vinylpgapi.service.AccessibilityService;
import br.com.acta.vinylpgapi.service.UserService;
import br.com.acta.vinylpgapi.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccessibilityService {
    private final UserAccessibilityRepository repo;
    private final UserService userService;
    private final AccessibilityService accessibilityService;

    public List<AccessibilityResp> list(Long userId, Long calledUserId){
        Validation.checkOwnership(userId, calledUserId);

        return repo.findByUserId(userId).stream()
                .map(u -> new AccessibilityResp(u.getAccessibility().getId(), u.getAccessibility().getName(), u.getAccessibility().getDescription()))
                .toList();
    }

    public UserAccessibilityResp select(Long userId, Long callerUserId, UserAccessibilityReq dto){
        Validation.checkOwnership(userId, callerUserId);

        User user = userService.getEntity(userId);
        Accessibility accessibility = accessibilityService.getEntity(dto.accessibilityId());

        boolean exists = repo.existsByUserIdAndAccessibilityId(userId, accessibility.getId());
        Validation.checkUniqueConstraint(exists, "User already has this accessibility");

        UserAccessibility userAccessibility = new UserAccessibility(user, accessibility);
        repo.save(userAccessibility);
        return new UserAccessibilityResp(userId, dto.accessibilityId());
    }

    @Transactional
    public void remove(Long userId, Long calledUserId, Long accessibilityId){
        Validation.checkOwnership(userId, calledUserId);

        boolean exists = repo.existsByUserIdAndAccessibilityId(userId, accessibilityId);
        if (!exists) throw new EntityNotFoundException("Accessibility");

        repo.deleteByUserIdAndAccessibilityId(userId, accessibilityId);
    }
}
