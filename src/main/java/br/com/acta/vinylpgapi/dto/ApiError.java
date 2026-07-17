package br.com.acta.vinylpgapi.dto;


import java.util.Map;

public record ApiError(
        String error,
        String message,
        Map<String, Object> details
) {
}
