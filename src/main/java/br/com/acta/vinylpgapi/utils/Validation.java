package br.com.acta.vinylpgapi.utils;

import br.com.acta.vinylpgapi.common.exceptions.ConflictException;
import br.com.acta.vinylpgapi.common.exceptions.ForbiddenException;

public class Validation {
    public static void checkOwnership(Long resourceId, Long userId) {
        if (!resourceId.equals(userId)) throw new ForbiddenException();
    }

    public static void checkUniqueConstraint(boolean exists, String field) {
        if (exists) throw new ConflictException(field);
    }

    public static void checkUniqueConstraint(boolean exists, String fieldOne, String fieldTwo){
        if (exists) throw new ConflictException(fieldOne, fieldTwo);
    }
}
