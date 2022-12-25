package com.alexandru.springbootecommerce.controller;

import com.alexandru.springbootecommerce.dto.BadCredentialsErrorResponse;
import com.alexandru.springbootecommerce.dto.ErrorResponse;
import com.alexandru.springbootecommerce.dto.ValidationExceptionResponse;
import com.alexandru.springbootecommerce.exceptions.AccountNotActivated;
import com.alexandru.springbootecommerce.exceptions.CategoryNotFoundException;
import com.alexandru.springbootecommerce.exceptions.JwtAuthenticationException;
import com.alexandru.springbootecommerce.exceptions.OrderNotFound;
import com.alexandru.springbootecommerce.exceptions.UserAlreadyExistsException;
import com.alexandru.springbootecommerce.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationExceptionResponse> validationExceptionResponses = ex.getBindingResult().getAllErrors().stream()
                .map(e -> new ValidationExceptionResponse(((FieldError) e).getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(validationExceptionResponses, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFound e, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(AccountNotActivated.class)
    public ResponseEntity<ErrorResponse> handleAccountNotActivatedException(AccountNotActivated e, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleJwtAuthenticationException(JwtAuthenticationException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BadCredentialsErrorResponse> handleBadCredentialsException() {
        BadCredentialsErrorResponse badCredentialsErrorResponse = BadCredentialsErrorResponse.builder()
                .message("Entered credentials are invalid!").build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(badCredentialsErrorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().title(e.getMessage()).details(request.getRequestURI()).build();
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ValidationExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request) {
        ValidationExceptionResponse validationExceptionResponse =
                new ValidationExceptionResponse("username", "Provided email is already used, please try another one");
        return ResponseEntity.badRequest().body(validationExceptionResponse);
    }
}
