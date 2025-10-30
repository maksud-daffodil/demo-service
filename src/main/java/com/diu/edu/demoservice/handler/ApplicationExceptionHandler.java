package com.diu.edu.demoservice.handler;


import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.ErrorDTO;
import com.diu.edu.demoservice.exception.ServiceBusinessException;
import com.diu.edu.demoservice.exception.ServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ApiDTO<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ApiDTO<?> serviceResponse = new ApiDTO<>();
        List<ErrorDTO> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        serviceResponse.setStatus(false);
        serviceResponse.setErrors(errors);
        serviceResponse.setMessage("Something Went Wrong!!");
        return serviceResponse;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ApiDTO<?> handleServiceExceptionUnique(DataIntegrityViolationException exception) {
        log.error("e: ", exception);
        ApiDTO<?> serviceResponse = new ApiDTO<>();
        serviceResponse.setStatus(false);
        serviceResponse.setMessage("Value is not unique!!");
        return serviceResponse;
    }

    @ExceptionHandler(ServiceBusinessException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ApiDTO<?> handleServiceException(ServiceBusinessException exception) {
        ApiDTO<?> serviceResponse = new ApiDTO<>();
        serviceResponse.setStatus(false);
        serviceResponse.setMessage(exception.getMessage());
//        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiDTO<?> handleServiceExceptionNotFound(ServiceNotFoundException exception) {
        ApiDTO<?> serviceResponse = new ApiDTO<>();
        serviceResponse.setStatus(false);
        serviceResponse.setMessage(exception.getMessage());
        return serviceResponse;
    }

}
