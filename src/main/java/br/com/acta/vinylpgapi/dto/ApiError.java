package br.com.acta.vinylpgapi.dto;

import java.util.List;

public record ApiError(
        List<String> messages,
        Integer status
) {
}
