package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.User;

import java.util.Optional;

public interface UserRepository extends RepositoryBase<User> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByCellphone(String cellphone);
    boolean existsByDocument(String document);
}
