package com.digitalacademy.ezbudgetservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long historyId;

    private String historyName;

    @NotNull
    private Double historyBalance;

    @NotNull
    private Long historyPlanActionId;

    @NotNull
    private Long historyPlanId;

    @NotNull
    private Long historyPlanPartnerId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Bangkok")
    @UpdateTimestamp
    private Date historyLastUpdate;

}
