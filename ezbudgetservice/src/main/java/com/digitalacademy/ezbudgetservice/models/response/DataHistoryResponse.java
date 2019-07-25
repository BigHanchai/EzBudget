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
public class DataHistoryResponse {

    @JsonProperty("history_date")
    private String  historyDate;

    @JsonProperty("date_sum_balance")
    private Double dateSumBalance;

    @JsonProperty("history")
    private ArrayList<HistoryResponse> historyResponses;
}
