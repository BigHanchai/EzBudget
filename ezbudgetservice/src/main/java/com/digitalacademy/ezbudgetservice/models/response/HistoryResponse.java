package com.digitalacademy.ezbudgetservice.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryResponse {

    @JsonProperty("history_date")
    private String  historyDate;

    @JsonProperty("history_time")
    private String  historyTime;

    @JsonProperty("history_name")
    private String  historyName;

    @JsonProperty("plan_action_id")
    private Long planActionId;

    @JsonProperty("history_balance")
    private Double historyBalance;
}
