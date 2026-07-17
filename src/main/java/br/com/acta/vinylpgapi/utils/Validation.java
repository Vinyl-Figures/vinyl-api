package br.com.acta.vinylpgapi.utils;

import br.com.acta.vinylpgapi.common.exceptions.ForbiddenException;

public class Validation {
    public static void checkOwnership(Long resourceId, Long userId) {
        if (!resourceId.equals(userId)) throw new ForbiddenException();
    }
}
