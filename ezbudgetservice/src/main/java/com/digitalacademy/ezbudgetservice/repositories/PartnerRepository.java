package com.digitalacademy.ezbudgetservice.repositories;

import com.digitalacademy.ezbudgetservice.models.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Partner findAllByPartnerCitizenIdAndPartnerPassword(String citizenId, String password);
}
