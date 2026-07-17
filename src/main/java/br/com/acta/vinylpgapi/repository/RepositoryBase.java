package br.com.acta.vinylpgapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryBase<ENT> extends JpaRepository<ENT, Long> {
}
