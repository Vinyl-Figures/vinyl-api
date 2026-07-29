package br.com.acta.vinylpgapi.repository;

import br.com.acta.vinylpgapi.model.Address;

import java.util.List;

public interface AddressRepository extends RepositoryBase<Address> {
    List<Address> findByUserId(Long userId);
}
