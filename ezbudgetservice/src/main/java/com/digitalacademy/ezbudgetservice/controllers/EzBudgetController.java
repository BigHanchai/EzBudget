package com.digitalacademy.ezbudgetservice.controllers;

import com.digitalacademy.ezbudgetservice.constants.StatusResponse;
import com.digitalacademy.ezbudgetservice.models.Partner;
import com.digitalacademy.ezbudgetservice.models.ResponseModel;
import com.digitalacademy.ezbudgetservice.models.StatusModel;
import com.digitalacademy.ezbudgetservice.models.response.*;
import com.digitalacademy.ezbudgetservice.services.EzBudgetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/esb")
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

    @GetMapping("/partner_code")
    public HttpEntity<ResponseModel> getPartnerByCode(@Valid @RequestHeader("partnerCode") String code) throws Exception{
        try {
            GetPartnerResponse ezBudgetList = ezBudgetService.getPartnerByCode(code);
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

    @GetMapping("/plan_summary")//Summary Record
    public HttpEntity<ResponseModel> getPlanByPartner(@Valid @RequestHeader("partnerID") Long id) throws Exception{
        try {
            GetListPlanResponse ezBudgetList = ezBudgetService.getPlanSummaryByPartner(id);
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

    @GetMapping("/plan_list")//Nerver die
    public HttpEntity<ResponseModel> getPlanNeverDieByPartner(@Valid @RequestHeader("partnerID") Long id) throws Exception{
        try {
            GetListPlanResponse ezBudgetList = ezBudgetService.getPlanNerverDieByPartner(id);
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

    @GetMapping("/history")//historyDetail
    public HttpEntity<ResponseModel> getHistoryDetail(@Valid @RequestHeader("planID") Long planID, @RequestHeader("partnerID") Long partnerID) throws Exception{
        try {
            GetHistoryResponse ezBudgetList = ezBudgetService.getHistoryByPlanId(planID, partnerID);
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

    @PostMapping({"/create_action"})
    public HttpEntity<ResponseModel> getCreateAction(@Valid @RequestHeader("planID") Long planID, @Valid @RequestHeader("planPartnerId") Long planPartnerId, @RequestBody GetPlanActionListResponse body) throws Exception {
        try {
            for(int j = 0; j < body.getGetPlanActionResponseArrayList().size(); ++j) {
                this.ezBudgetService.createPlanDetails(planID, planPartnerId, ((GetPlanActionResponse)body.getGetPlanActionResponseArrayList().get(j)).getActionId(), ((GetPlanActionResponse)body.getGetPlanActionResponseArrayList().get(j)).getActionBalance());
            }

            StatusModel status = new StatusModel(StatusResponse.GET_CREATED_SUCCESS.getCode(), StatusResponse.GET_CREATED_SUCCESS.getMessage());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(status));
        } catch (Exception var6) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;
            return (new ResponseModel(new StatusModel(statusResponse.getCode(), statusResponse.getMessage()))).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/action_list")//historyDetail
    public HttpEntity<ResponseModel> getActionList(@Valid @RequestHeader("planID") Long planID, @RequestHeader("partnerID") Long partnerID) throws Exception{
        try {
            GetPlanDetailsResponse ezBudgetList = ezBudgetService.getPlanDetailsByPlanId(planID,partnerID);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_CREATED_SUCCESS.getCode(), StatusResponse.GET_CREATED_SUCCESS.getMessage()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(status));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
