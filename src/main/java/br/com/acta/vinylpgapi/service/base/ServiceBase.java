package br.com.acta.vinylpgapi.service.base;

import br.com.acta.vinylpgapi.common.exceptions.EntityNotFoundException;
import br.com.acta.vinylpgapi.repository.RepositoryBase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class ServiceBase<CREATE_REQ, UPDATE_REQ, RESP, ENT> {
    protected final RepositoryBase<ENT> repo;
    protected abstract String entityName();

    protected abstract RESP toResponse(ENT ent);
    protected abstract ENT toEntity(CREATE_REQ dto);

    public ENT getEntity(Long id){
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(entityName(), id));
    }

    public RESP create(CREATE_REQ dto){
        ENT ent = toEntity(dto);

        ENT saved = repo.save(ent);
        return toResponse(saved);
    }

    public RESP getById(Long id){
        ENT ent = getEntity(id);
        return toResponse(ent);
    }

    public List<RESP> listAll(){
        List<ENT> entList = repo.findAll();

        return entList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    protected abstract ENT updateEntity(ENT ent, UPDATE_REQ dto);

    public RESP patch(Long id, UPDATE_REQ dto){
        ENT ent = getEntity(id);

        ENT updatedEnt = updateEntity(ent, dto);

        ENT saved = repo.save(updatedEnt);
        return toResponse(saved);
    }

    public void deleteById(Long id){
        ENT ent = getEntity(id);
        repo.delete(ent);
    }
}
