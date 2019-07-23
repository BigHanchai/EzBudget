package com.digitalacademy.ezbudgetservice.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long partnerId;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner Code size between 1 - 255")
    private String partnerCode;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner Fname Th size between 1 - 255")
    private String partnerFnameTh;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner Fname Eng size between 1 - 255")
    private String partnerFnameEng;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner Lname Th size between 1 - 255")
    private String partnerLnameTh;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner Lname Eng size between 1 - 255")
    private String partnerLnameEng;

    @Column(name = "partner_balance", nullable = false, columnDefinition = "double default 100000")
    private Double PartnerBalance;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner citizen id size between 1 - 255")
    private String partnerCitizenId;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner password size between 1 - 255")
    private String partnerPassword;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your partner resource owner id size between 1 - 255")
    private String partnerResourceOwnerId;

    @Column
    @UpdateTimestamp
    private Date PartnerLastUpdate;

}