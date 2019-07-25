package com.digitalacademy.ezbudgetservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planId;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your plan name size between 1 - 255")
    private String planName;

    @NotNull
    private Double planBalance;

    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Bangkok")
    private Date planStartDate;

    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Bangkok")
    private Date planEndDate;


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Bangkok")
    @UpdateTimestamp
    private Date planLastUpdate;

    @NotNull
    private Long planPartnerId;

}
