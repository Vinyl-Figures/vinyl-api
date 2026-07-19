package br.com.acta.vinylpgapi.common;

import br.com.acta.vinylpgapi.common.exceptions.*;
import br.com.acta.vinylpgapi.dto.ApiError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflict(ConflictException ce){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(List.of(ce.getMessage()), 409));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException enfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(List.of(enfe.getMessage()), 404));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleForbidden(ForbiddenException fe){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiError(List.of(fe.getMessage()), 403));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorized(UnauthorizedException ue){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiError(List.of(ue.getMessage()), 401));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidation(ValidationException ve){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(List.of(ve.getMessage()), 400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException manve){
        List<String> mensagens = manve.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(mensagens, 400));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleIllegalState(IllegalStateException ise){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(List.of(ise.getMessage()), 400));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraint(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(List.of("Some of the parameters are invalid"), 400));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleValidation(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(List.of("The request body is invalid or malformed"), 400));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingParam(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(List.of("A mandatory parameter was not informed"), 400));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodMismatch(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(List.of("A parameter was informed with an invalid type"), 400));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResourceFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(List.of("The requested resource was not found"), 404));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleHttpRequest(){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ApiError(List.of("The HTTP method used is not allowed for this route"), 405));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiError> handleHttpMedia(){
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new ApiError(List.of("The requested media type is not supported"), 415));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(List.of("The operation could not be performed due to a conflict with existing data"), 409));
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ApiError> handleOptimisticLocking(){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(List.of("The record was modified by another operation, please try again"), 409));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiError(List.of("You do not have permission to access this resource"), 403));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(List.of("An unexpected error occurred during execution"), 500));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(List.of("An unexpected error occurred during execution"), 500));
    }
}