package com.clinic.medAsist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        Map<String, Object> errorMap = new HashMap<> ();
        errorMap.put ("timestamp", LocalDateTime.now ());
        errorMap.put ("status", HttpStatus.BAD_REQUEST.value ());
        errorMap.put ("error", "Bad Request");
        errorMap.put ("message", userAlreadyExistsException.getMessage ());
        return new ResponseEntity<> (errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleRoleNotFoundException(RoleNotFoundException roleNotFoundException){
        Map<String, Object> errorMap = new HashMap<> ();
        errorMap.put ("timestamp", LocalDateTime.now ());
        errorMap.put ("status", HttpStatus.NOT_FOUND.value ());
        errorMap.put ("error", "Not found");
        errorMap.put ("message", roleNotFoundException.getMessage ());
        return new ResponseEntity<> (errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        Map<String, Object> errorMap = new HashMap<> ();
        errorMap.put ("timestamp", LocalDateTime.now ());
        errorMap.put ("status", HttpStatus.NOT_FOUND.value ());
        errorMap.put ("error", "Not found");
        errorMap.put ("message", userNotFoundException.getMessage ());
        return new ResponseEntity<> (errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<?> handlePasswordMismatchException(PasswordMismatchException passwordMismatchException){
        Map<String, Object> errorMap = new HashMap<> ();
        errorMap.put ("timestamp", LocalDateTime.now ());
        errorMap.put ("status", HttpStatus.BAD_REQUEST.value ());
        errorMap.put ("error", "Bad Request");
        errorMap.put ("message", passwordMismatchException.getMessage ());
        return new ResponseEntity<> (errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TermsAndPrivacyNotAcceptedException.class)
    public ResponseEntity<?> handleTermsAndPrivacyNotAcceptedException(TermsAndPrivacyNotAcceptedException termsAndPrivacyNotAcceptedException){
        Map<String, Object> errorMap = new HashMap<> ();
        errorMap.put ("timestamp", LocalDateTime.now ());
        errorMap.put ("status", HttpStatus.BAD_REQUEST.value ());
        errorMap.put ("error", "Bad Request");
        errorMap.put ("message", termsAndPrivacyNotAcceptedException.getMessage ());
        return new ResponseEntity<> (errorMap, HttpStatus.BAD_REQUEST);
    }
}

