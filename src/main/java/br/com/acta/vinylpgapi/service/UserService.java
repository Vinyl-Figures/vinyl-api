package br.com.acta.vinylpgapi.service;

import br.com.acta.vinylpgapi.common.exceptions.ConflictException;
import br.com.acta.vinylpgapi.dto.user.CreateUserReq;
import br.com.acta.vinylpgapi.dto.user.UpdateUserReq;
import br.com.acta.vinylpgapi.dto.user.UserResp;
import br.com.acta.vinylpgapi.model.User;
import br.com.acta.vinylpgapi.repository.UserRepository;
import br.com.acta.vinylpgapi.service.base.ServiceBase;
import br.com.acta.vinylpgapi.utils.Hash;
import br.com.acta.vinylpgapi.utils.Validation;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceBase<CreateUserReq, UpdateUserReq, UserResp, User> {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        super(repo);
        this.repo = repo;
    }

    @Override
    protected String entityName() {
        return "User";
    }

    @Override
    protected UserResp toResponse(User user) {
        return new UserResp(user.getId(), user.getName(), user.getDocument(), user.getCellphone(), user.getEmail());
    }

    @Override
    protected User toEntity(CreateUserReq dto) {
        return new User(
                dto.name(),
                dto.document(),
                dto.cellphone(),
                dto.email(),
                Hash.generateHash(dto.password())
        );
    }

    @Override
    protected User updateEntity(User user, UpdateUserReq dto) {
        if (dto.name() != null) user.setName(dto.name());
        if (dto.cellphone() != null) user.setCellphone(dto.cellphone());
        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.password() != null) user.setPassword(Hash.generateHash(dto.password()));
        return user;
    }

    @Override
    public UserResp create(CreateUserReq dto) {
        if (repo.existsByDocument(dto.document())) throw new ConflictException("document");
        if (repo.existsByEmail(dto.email())) throw new ConflictException("email");
        if (repo.existsByCellphone(dto.cellphone())) throw new ConflictException("cellphone");

        return super.create(dto);
    }

    public UserResp getUser(Long userId, Long callerUserId){
        Validation.checkOwnership(userId, callerUserId);
        return super.getById(userId);
    }

    public UserResp patchUser(Long userId, Long callerUserId, UpdateUserReq dto){
        Validation.checkOwnership(userId, callerUserId);
        if (dto.email() != null && repo.existsByEmail(dto.email())) throw new ConflictException("email");
        if (dto.cellphone() != null && repo.existsByCellphone(dto.cellphone())) throw new ConflictException("cellphone");

        return super.patch(userId, dto);
    }

    public void deleteUser(Long userId, Long callerUserId){
        Validation.checkOwnership(userId, callerUserId);
        super.deleteById(userId);
    }
}
