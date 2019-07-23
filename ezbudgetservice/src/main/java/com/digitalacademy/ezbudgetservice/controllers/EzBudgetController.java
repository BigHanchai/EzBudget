package com.digitalacademy.ezbudgetservice.controllers;

import com.digitalacademy.ezbudgetservice.constants.StatusResponse;
import com.digitalacademy.ezbudgetservice.models.Partner;
import com.digitalacademy.ezbudgetservice.models.ResponseModel;
import com.digitalacademy.ezbudgetservice.models.StatusModel;
import com.digitalacademy.ezbudgetservice.models.response.GetPartnerResponse;
import com.digitalacademy.ezbudgetservice.services.EzBudgetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/ezbudget")
public class EzBudgetController {
    public static final Logger log = LogManager.getLogger(EzBudgetController.class.getName());

    private EzBudgetService ezBudgetService;

    @Autowired
    public EzBudgetController(EzBudgetService ezBudgetService) {
        this.ezBudgetService = ezBudgetService;
    }

    @GetMapping("/partner")
    public HttpEntity<ResponseModel> getPartner(@Valid @RequestHeader("CitizenId") String citizenId, @RequestHeader("Password") String password) throws Exception{
        try {
            GetPartnerResponse ezBudgetList = ezBudgetService.getPartner(citizenId, password);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, ezBudgetList));
        }catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
