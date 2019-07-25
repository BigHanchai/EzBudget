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
public class GetHistoryResponse {

    private String planName;
    private Double planBalance;
    private Double overAllUse;

    @JsonProperty("sum_action")
    private ArrayList<SumActionResponse> sumActionResponses;

    @JsonProperty("data_history")
    private ArrayList<DataHistoryResponse> dataHistoryResponses;

    public GetHistoryResponse() {
    }

    public GetHistoryResponse(ArrayList<SumActionResponse> sumActionResponses, ArrayList<DataHistoryResponse> dataHistoryResponses) {
        this.sumActionResponses = sumActionResponses;
        this.dataHistoryResponses = dataHistoryResponses;
    }
}
