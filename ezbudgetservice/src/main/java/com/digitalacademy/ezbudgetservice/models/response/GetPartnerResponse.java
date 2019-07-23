package com.digitalacademy.ezbudgetservice.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.util.Date;


@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPartnerResponse {

    private Long partnerId;
    private String partnerCode;
    private String partnerFnameTh;
    private String partnerFnameEng;
    private String partnerLnameTh;
    private String partnerLnameEng;
    private Double PartnerBalance;
    private String partnerResourceOwnerId;


}