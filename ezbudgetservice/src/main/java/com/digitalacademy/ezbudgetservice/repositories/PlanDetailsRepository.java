package com.digitalacademy.ezbudgetservice.repositories;

import com.digitalacademy.ezbudgetservice.models.PlanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanDetailsRepository extends JpaRepository<PlanDetails, Long> {

    PlanDetails findAllByPlanDetailsPlanId(Long id);

}
