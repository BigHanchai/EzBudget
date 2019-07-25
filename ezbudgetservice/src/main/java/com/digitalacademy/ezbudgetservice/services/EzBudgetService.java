package com.digitalacademy.ezbudgetservice.services;

import com.digitalacademy.ezbudgetservice.models.History;
import com.digitalacademy.ezbudgetservice.models.Partner;
import com.digitalacademy.ezbudgetservice.models.Plan;
import com.digitalacademy.ezbudgetservice.models.PlanDetails;
import com.digitalacademy.ezbudgetservice.models.response.*;
import com.digitalacademy.ezbudgetservice.repositories.HistoryRepository;
import com.digitalacademy.ezbudgetservice.repositories.PartnerRepository;
import com.digitalacademy.ezbudgetservice.repositories.PlanDetailsRepository;
import com.digitalacademy.ezbudgetservice.repositories.PlanRepository;
import com.digitalacademy.ezbudgetservice.models.*;
import com.digitalacademy.ezbudgetservice.repositories.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EzBudgetService {

    public static final Logger log = LogManager.getLogger(EzBudgetService.class.getName());

    private PartnerRepository partnerRepository;
    private PlanRepository planRepository;
    private HistoryRepository historyRepository;
    private PlanDetailsRepository planDetailsRepository;

    private PlanActionRepository planActionRepository;

    public EzBudgetService(PartnerRepository partnerRepository, PlanRepository planRepository, HistoryRepository historyRepository, PlanDetailsRepository planDetailsRepository, PlanActionRepository planActionRepository) {
        this.partnerRepository = partnerRepository;
        this.planRepository = planRepository;
        this.historyRepository = historyRepository;
        this.planDetailsRepository = planDetailsRepository;

        this.planActionRepository = planActionRepository;
    }

    public GetPartnerResponse getPartner(String citizenId, String password) throws Exception{
        Partner partner = partnerRepository.findAllByPartnerCitizenIdAndPartnerPassword(citizenId, password);

            GetPartnerResponse getPartnerResponse = new GetPartnerResponse();
            getPartnerResponse.setPartnerId(partner.getPartnerId());
            getPartnerResponse.setPartnerCode(partner.getPartnerCode());
            getPartnerResponse.setPartnerFnameTh(partner.getPartnerFnameTh());
            getPartnerResponse.setPartnerFnameEng(partner.getPartnerFnameEng());
            getPartnerResponse.setPartnerLnameTh(partner.getPartnerLnameTh());
            getPartnerResponse.setPartnerLnameEng(partner.getPartnerLnameEng());
            getPartnerResponse.setPartnerBalance(partner.getPartnerBalance());
            getPartnerResponse.setPartnerResourceOwnerId(partner.getPartnerResourceOwnerId());

        return getPartnerResponse;
    }

    public GetPartnerResponse getPartnerByCode(String code) throws Exception{
        Partner partner = partnerRepository.findAllByPartnerCode(code);

        GetPartnerResponse getPartnerResponse = new GetPartnerResponse();
        getPartnerResponse.setPartnerId(partner.getPartnerId());
        getPartnerResponse.setPartnerCode(partner.getPartnerCode());
        getPartnerResponse.setPartnerFnameTh(partner.getPartnerFnameTh());
        getPartnerResponse.setPartnerFnameEng(partner.getPartnerFnameEng());
        getPartnerResponse.setPartnerLnameTh(partner.getPartnerLnameTh());
        getPartnerResponse.setPartnerLnameEng(partner.getPartnerLnameEng());
        getPartnerResponse.setPartnerBalance(partner.getPartnerBalance());
        getPartnerResponse.setPartnerResourceOwnerId(partner.getPartnerResourceOwnerId());

        return getPartnerResponse;
    }

    public GetListPlanResponse getPlanSummaryByPartner(Long id) throws Exception{
        List<Plan> planlist = planRepository.findAllByPlanPartnerIdOrderByPlanStartDateDesc(id);
        ArrayList<GetPlanResponse> getPlanResponseArrayList = new ArrayList<>();
        Date date= new Date();
        long today = date.getTime();
        for (int j = 0; j < planlist.size(); j++) {
           // log.info(" Start Date: "+planlist.get(j).getPlanStartDate());
            //log.info("Time Start Date: "+planlist.get(j).getPlanStartDate().getTime());
            if (planlist.get(j).getPlanEndDate().getTime() <= today ||( planlist.get(j).getPlanStartDate().getTime() <= today && planlist.get(j).getPlanEndDate().getTime() >= today)) {
                GetPlanResponse getPlanResponse = new GetPlanResponse();
                getPlanResponse.setPlanId(planlist.get(j).getPlanId());
                getPlanResponse.setPlanName(planlist.get(j).getPlanName());
                getPlanResponse.setPlanPrice(planlist.get(j).getPlanBalance());
                getPlanResponse.setPlanStartDate(planlist.get(j).getPlanStartDate());
                getPlanResponse.setPlanEndDate(planlist.get(j).getPlanEndDate());
                getPlanResponseArrayList.add(getPlanResponse);
            }
        }

        GetListPlanResponse getListPlanResponse = new GetListPlanResponse(getPlanResponseArrayList);

        return getListPlanResponse;
    }

    public GetListPlanResponse getPlanNerverDieByPartner(Long id) throws Exception{
        LocalDate localDate = LocalDate.now();
        List<Plan> planlist = planRepository.findAllByPlanPartnerIdOrderByPlanStartDateAsc(id);
        Date date= new Date();
        long today = date.getTime();

        ArrayList<GetPlanResponse> getPlanResponseArrayList = new ArrayList<>();
        for (int j = 0; j < planlist.size(); j++) {
            GetPlanResponse getPlanResponse = new GetPlanResponse();
            //log.info("Time End Date: "+planlist.get(j).getPlanEndDate().getTime());
            if (planlist.get(j).getPlanEndDate().getTime() >= today) {
                getPlanResponse.setPlanId(planlist.get(j).getPlanId());
                getPlanResponse.setPlanName(planlist.get(j).getPlanName());
                getPlanResponse.setPlanPrice(planlist.get(j).getPlanBalance());
                getPlanResponse.setPlanStartDate(planlist.get(j).getPlanStartDate());
                getPlanResponse.setPlanEndDate(planlist.get(j).getPlanEndDate());
                getPlanResponseArrayList.add(getPlanResponse);
            }
        }

        GetListPlanResponse getListPlanResponse = new GetListPlanResponse(getPlanResponseArrayList);

        return getListPlanResponse;
    }

    public GetHistoryResponse getHistoryByPlanId(Long planID, Long partnerID) throws Exception{
        GetHistoryResponse getHistoryResponse = new GetHistoryResponse();

        Plan plan= planRepository.findAllByPlanId(planID);
        getHistoryResponse.setPlanName(plan.getPlanName());
        getHistoryResponse.setPlanBalance(plan.getPlanBalance());

        Double over_all_use = historyRepository.sumAmountByHistoryBalance(planID,partnerID);
        getHistoryResponse.setOverAllUse(over_all_use);

        List<Long> actionList = historyRepository.selectActionGroupByActionASC(planID,partnerID);
        ArrayList<SumActionResponse> sumActionResponseList = new ArrayList<>();
        for(int i=0; i<actionList.size(); i++){
            SumActionResponse sumActionResponse = new SumActionResponse();

            sumActionResponse.setPlanActionId(actionList.get(i));
            sumActionResponse.setSumBalanceAction(historyRepository.sumAmountByHistoryBalanceByAction(planID,partnerID,actionList.get(i)));

            sumActionResponseList.add(sumActionResponse);
        }

        getHistoryResponse.setSumActionResponses(sumActionResponseList);

        List<History> historyList = historyRepository.findAllByHistoryPlanIdAndHistoryPlanPartnerIdOrderByHistoryLastUpdateDesc(planID,partnerID);
        ArrayList<HistoryResponse> historyResponseArrayList = new ArrayList<>();
        for(int j=0; j<historyList.size(); j++){
            HistoryResponse historyResponse = new HistoryResponse();
            historyResponse.setHistoryName(historyList.get(j).getHistoryName());
            historyResponse.setPlanActionId(historyList.get(j).getHistoryPlanActionId());
            historyResponse.setHistoryBalance(historyList.get(j).getHistoryBalance());

            String[] arrDate = historyList.get(j).getHistoryLastUpdate().toString().split(" ");
            String arrTime = arrDate[1].substring(0,8);
            historyResponse.setHistoryDate(arrDate[0]);
            historyResponse.setHistoryTime(arrTime);
            historyResponseArrayList.add(historyResponse);
        }
        getHistoryResponse.setHistoryResponses(historyResponseArrayList);
        return getHistoryResponse;
    }

    public PlanDetails createPlanDetails (Long planId,Long planPartnerId,Long planActionID,Double planBalance) throws Exception{
        PlanDetails planDetails = new PlanDetails();
        planDetails.setPlanDetailsPlanId(planId);
        planDetails.setPlanDetailsPlanPartnerId(planPartnerId);
        planDetails.setPlanDetailsBalance(planBalance);
        planDetails.setPlanDetailsPlanActionId(planActionID);
        return planDetailsRepository.save(planDetails);
    }
    public GetPlanDetailsResponse getPlanDetailsByPlanId(Long planId, Long partnerID) throws Exception {

        List<PlanDetails> planDetails = planDetailsRepository.findAllByPlanDetailsPlanIdAndPlanDetailsPlanPartnerId(planId, partnerID);
        ArrayList<PlanDetailsResponse> planDetailsResponseArrayList = new ArrayList<>();
        for(int i=0; i<planDetails.size(); i++){
            PlanDetailsResponse planDetailsResponse = new PlanDetailsResponse();
            PlanAction planAction = planActionRepository.findAllByPlanActionId(planDetails.get(i).getPlanDetailsPlanActionId());
            planDetailsResponse.setPlanActionId(planDetails.get(i).getPlanDetailsPlanActionId());
            planDetailsResponse.setPlanActionName(planAction.getPlanActionName());
            planDetailsResponse.setPlanDetailsBalance(planDetails.get(i).getPlanDetailsBalance());
            planDetailsResponseArrayList.add(planDetailsResponse);
        }
        return new GetPlanDetailsResponse(planDetailsResponseArrayList);
    }

}
