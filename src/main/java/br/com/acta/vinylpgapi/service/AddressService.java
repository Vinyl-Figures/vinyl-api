package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.dto.addresses.AddressReq;
import br.com.acta.vinylpgapi.dto.addresses.AddressResp;
import br.com.acta.vinylpgapi.model.Address;
import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.repository.AddressRepository;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import br.com.acta.vinylpgapi.utils.Validation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService extends ServiceBase<AddressReq, AddressReq, AddressResp, Address> {
    private final AddressRepository repo;
    private final UserService userService;

    public AddressService(AddressRepository repo, AddressRepository repo1, UserService userService) {
        super(repo);
        this.repo = repo1;
        this.userService = userService;
    }

    @Override
    protected String entityName() {
        return "Address";
    }

    @Override
    protected AddressResp toResponse(Address address) {
        return new AddressResp(
                address.getId(),
                address.getNumber(),
                address.getComplement(),
                address.getZipCode(),
                address.getUser().getId()
        );
    }

    @Override
    protected Address toEntity(AddressReq dto) {
        return new Address(
                dto.number(),
                dto.complement(),
                dto.zipCode()
        );
    }

    @Override
    protected Address updateEntity(Address address, AddressReq dto) {
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setZipCode(dto.zipCode());
        return address;
    }

    public AddressResp create(AddressReq dto, Long userId) {
        User user = userService.getEntity(userId);
        Address address = toEntity(dto);

        address.setUser(user);
        return toResponse(repo.save(address));
    }

    public List<AddressResp> listByUser(Long userId, Long callerUserId){
        Validation.checkOwnership(userId, callerUserId);
        return repo.findByUserId(userId).stream().map(this::toResponse).toList();
    }

    public AddressResp getOwned(Long addressId, Long callerUserId){
        return toResponse(requireOwned(addressId, callerUserId));
    }

    public AddressResp patchOwned(Long addressId, Long callerUserId, AddressReq dto){
        Address address = requireOwned(addressId, callerUserId);
        return toResponse(repo.save(updateEntity(address, dto)));
    }

    public void deleteOwned(Long addressId, Long calledUserId){
        repo.delete(requireOwned(addressId, calledUserId));
    }

    private Address requireOwned(Long addressId, Long callerUserId){
        Address address = getEntity(addressId);
        Validation.checkOwnership(address.getUser().getId(), callerUserId);
        return address;
    }
}
