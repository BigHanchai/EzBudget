package com.digitalacademy.ezbudgetservice.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPlanResponse {
    private Long planId;
    private String planName;
    private Double planPrice;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Bangkok")
    private Date planStartDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Bangkok")
    private Date planEndDate;
}
