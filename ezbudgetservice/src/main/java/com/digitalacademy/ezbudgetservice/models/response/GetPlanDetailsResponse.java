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
public class GetPlanDetailsResponse {
    @JsonProperty("plan_action")
    private ArrayList<PlanDetailsResponse> planDetailsResponses;

    public GetPlanDetailsResponse(ArrayList<PlanDetailsResponse> planDetailsResponses) {
        this.planDetailsResponses = planDetailsResponses;
    }
}
