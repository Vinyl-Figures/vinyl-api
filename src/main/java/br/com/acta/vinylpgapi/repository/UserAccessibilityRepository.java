package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.join.UserAccessibility;

import java.util.List;

public interface UserAccessibilityRepository extends RepositoryBase<UserAccessibility> {
    List<UserAccessibility> findByUserId(Long userId);
    boolean existsByUserIdAndAccessibilityId(Long userId, Long accessibilityId);
    void deleteByUserIdAndAccessibilityId(Long userId, Long accessibilityId);
}
