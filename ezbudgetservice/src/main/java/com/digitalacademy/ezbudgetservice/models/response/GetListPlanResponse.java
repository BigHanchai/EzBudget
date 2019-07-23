package com.digitalacademy.ezbudgetservice.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListPlanResponse {
    @JsonProperty("plan_list")
    private ArrayList<GetPlanResponse> getPlanResponseArrayList;

    public GetListPlanResponse(ArrayList<GetPlanResponse> getPlanResponseArrayList) {
        this.getPlanResponseArrayList = getPlanResponseArrayList;
    }
}
