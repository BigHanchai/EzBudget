package com.digitalacademy.ezbudgetservice.services;

import com.digitalacademy.ezbudgetservice.models.Partner;
import com.digitalacademy.ezbudgetservice.models.response.GetPartnerResponse;
import com.digitalacademy.ezbudgetservice.repositories.PartnerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EzBudgetService {

    public static final Logger log = LogManager.getLogger(EzBudgetService.class.getName());

    private PartnerRepository partnerRepository;

    public EzBudgetService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
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
}
