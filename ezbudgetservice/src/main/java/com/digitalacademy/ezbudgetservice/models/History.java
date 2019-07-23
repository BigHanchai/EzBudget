package com.digitalacademy.ezbudgetservice.models;

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

    @NotNull
    private Double historyPrice;

    @NotNull
    private Long historyPlanActionId;

    @NotNull
    private Long historyPlanId;

    @NotNull
    private Long historyPlanPartnerId;

    @Column
    @UpdateTimestamp
    private Date historyLastUpdate;

}
