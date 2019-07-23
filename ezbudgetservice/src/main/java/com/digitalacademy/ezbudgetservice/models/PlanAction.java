package com.digitalacademy.ezbudgetservice.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class PlanAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planActionId;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your plan action name size between 1 - 255")
    private String planActionName;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your plan action details size between 1 - 255")
    private String planActionDetails;

    @Size(min = 1, max = 255, message = "Please type your plan action icon size between 1 - 255")
    private String planActionIcon;

    @NotNull
    @Column(name = "plan_action_active", nullable = false, columnDefinition = "String default Y")
    private String planActionActive;

    @Column
    @UpdateTimestamp
    private Date planActionLastUpdate;
}