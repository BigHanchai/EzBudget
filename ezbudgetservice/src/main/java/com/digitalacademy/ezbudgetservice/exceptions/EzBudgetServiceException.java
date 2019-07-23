package com.digitalacademy.ezbudgetservice.exceptions;

import com.digitalacademy.ezbudgetservice.constants.StatusResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EzBudgetServiceException extends Exception {

    private HttpStatus httpStatus;
    private StatusResponse statusResponse;

    public EzBudgetServiceException(StatusResponse statusResponse, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.statusResponse = statusResponse;
    }
}