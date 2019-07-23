package com.digitalacademy.ezbudgetservice.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class PlanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planDetailsId;

    @NotNull
    private Double planDetailsPrice;

    @NotNull
    private Long planDetailsPlanActionId;

    @NotNull
    private Long planDetailsPlanId;

    @NotNull
    private Long planDetailsPlanPartnerId;

    @Column
    @UpdateTimestamp
    private Date planDetailsLastUpdate;

}
