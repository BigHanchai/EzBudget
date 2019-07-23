package com.digitalacademy.ezbudgetservice.services;

import com.digitalacademy.ezbudgetservice.models.Partner;
import com.digitalacademy.ezbudgetservice.models.Plan;
import com.digitalacademy.ezbudgetservice.models.response.GetListPlanResponse;
import com.digitalacademy.ezbudgetservice.models.response.GetPartnerResponse;
import com.digitalacademy.ezbudgetservice.models.response.GetPlanResponse;
import com.digitalacademy.ezbudgetservice.repositories.PartnerRepository;
import com.digitalacademy.ezbudgetservice.repositories.PlanRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EzBudgetService {

    public static final Logger log = LogManager.getLogger(EzBudgetService.class.getName());

    private PartnerRepository partnerRepository;
    private PlanRepository planRepository;

    public EzBudgetService(PartnerRepository partnerRepository, PlanRepository planRepository) {
        this.partnerRepository = partnerRepository;
        this.planRepository = planRepository;
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

    public GetListPlanResponse getPlanByPartner(Long id) throws Exception{
        List<Plan> planlist = planRepository.findAllByPlanPartnerId(id);
        ArrayList<GetPlanResponse> getPlanResponseArrayList = new ArrayList<>();
        for (int j = 0; j < planlist.size(); j++) {
            GetPlanResponse getPlanResponse = new GetPlanResponse();
            getPlanResponse.setPlanId(planlist.get(j).getPlanId());
            getPlanResponse.setPlanName(planlist.get(j).getPlanName());
            getPlanResponse.setPlanPrice(planlist.get(j).getPlanPrice());
            getPlanResponse.setPlanStartDate(planlist.get(j).getPlanStartDate());
            getPlanResponse.setPlanEndDate(planlist.get(j).getPlanEndDate());
            getPlanResponseArrayList.add(getPlanResponse);
        }

        GetListPlanResponse getListPlanResponse = new GetListPlanResponse(getPlanResponseArrayList);

        return getListPlanResponse;
    }

    public GetListPlanResponse getPlanSummaryByPartner(Long id) throws Exception{
        LocalDate localDate = LocalDate.now();
        List<Plan> planlist = planRepository.findAllByPlanPartnerId(id);
        String today = (DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate));
        ArrayList<GetPlanResponse> getPlanResponseArrayList = new ArrayList<>();
        for (int j = 0; j < planlist.size(); j++) {
//            GetPlanResponse getPlanResponse = new GetPlanResponse();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//            String strDate = dateFormat.format(planlist.get(j).getPlanEndDate());
//            String[] strDate_list = strDate.split(" ");
//            if (strDate_list[0] == today) {
//                continue;
//            }else{
//                getPlanResponse.setPlanId(planlist.get(j).getPlanId());
//                getPlanResponse.setPlanName(planlist.get(j).getPlanName());
//                getPlanResponse.setPlanPrice(planlist.get(j).getPlanPrice());
//                getPlanResponse.setPlanStartDate(planlist.get(j).getPlanStartDate());
//                getPlanResponse.setPlanEndDate(planlist.get(j).getPlanEndDate());
//                getPlanResponseArrayList.add(getPlanResponse);
//            }
        }

        GetListPlanResponse getListPlanResponse = new GetListPlanResponse(getPlanResponseArrayList);

        return getListPlanResponse;
    }
}
