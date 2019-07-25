package com.digitalacademy.ezbudgetservice.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SumActionResponse {

    @JsonProperty("plan_action_id")
    private Long planActionId;

    @JsonProperty("plan_action_name")
    private String planActionName;

    @JsonProperty("sum_balance_action")
    private Double sumBalanceAction;
}
