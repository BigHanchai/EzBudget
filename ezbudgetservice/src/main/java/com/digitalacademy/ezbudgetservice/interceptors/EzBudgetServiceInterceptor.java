package com.digitalacademy.ezbudgetservice.interceptors;

import com.digitalacademy.ezbudgetservice.constants.StatusResponse;
import com.digitalacademy.ezbudgetservice.exceptions.EzBudgetServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EzBudgetServiceInterceptor implements HandlerInterceptor {
    public static final Logger log = LogManager.getLogger(EzBudgetServiceInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (response.getStatus() == 404) {
            StatusResponse statusResponse = StatusResponse.GET_REQUEST_WRONG_URL_PATH;
            throw new EzBudgetServiceException(statusResponse, HttpStatus.NOT_FOUND);
        }
        return true;
    }
}
