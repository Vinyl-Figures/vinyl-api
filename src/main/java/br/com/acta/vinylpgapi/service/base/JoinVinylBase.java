package br.com.acta.vinylpgapi.service.base;

import br.com.acta.vinylpgapi.model.Vinyl;

import java.util.List;

public interface JoinVinylBase<RESP, JOIN_REQ, JOIN_RESP> {
    List<RESP> list(Long vinylId);
    JOIN_RESP associate(Long vinylId, JOIN_REQ dto);
    void remove(Long vinylId, Long id);
    Vinyl getEntity(Long vinylId);
}
