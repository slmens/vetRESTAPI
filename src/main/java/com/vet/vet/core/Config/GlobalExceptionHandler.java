package com.vet.vet.core.Config;

import com.vet.vet.core.exception.RecordNotFoundException;
import com.vet.vet.core.result.ResultData;
import com.vet.vet.core.utils.GenerateResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();

        return new ResponseEntity<>(GenerateResult.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ResultData<String>> recordNotFoundException(RecordNotFoundException e){
        String dataAccessError = e.getLocalizedMessage();

        return new ResponseEntity<>(GenerateResult.dataAccessError(dataAccessError),HttpStatus.BAD_REQUEST);
    }
}
