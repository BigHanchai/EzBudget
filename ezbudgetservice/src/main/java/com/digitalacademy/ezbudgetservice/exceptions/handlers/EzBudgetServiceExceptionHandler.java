package com.digitalacademy.ezbudgetservice.exceptions.handlers;

import com.digitalacademy.ezbudgetservice.constants.StatusResponse;
import com.digitalacademy.ezbudgetservice.exceptions.EzBudgetServiceException;
import com.digitalacademy.ezbudgetservice.models.ResponseModel;
import com.digitalacademy.ezbudgetservice.models.StatusModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class EzBudgetServiceExceptionHandler {
    @ExceptionHandler(value = {EzBudgetServiceException.class})
    public HttpEntity<ResponseModel> handleExamServiceException(EzBudgetServiceException e) {
        return new ResponseModel(new StatusModel(
                e.getStatusResponse().getCode(),
                e.getStatusResponse().getMessage())
        ).build(e.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public HttpEntity<ResponseModel> handleHttpRequestMethodNotSupportedException() {
        StatusResponse statusResponse = StatusResponse.GET_REQUEST_WRONG_URL_PATH;

        return new ResponseModel(
                new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
        ).build(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, HttpMediaTypeNotSupportedException.class, MissingRequestHeaderException.class})
    public HttpEntity<ResponseModel> handleReturnBadRequest() {
        StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;

        return new ResponseModel(
                new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
        ).build(HttpStatus.BAD_REQUEST);
    }
}
