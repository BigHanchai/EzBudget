package com.digitalacademy.ezbudgetservice.repositories;

import com.digitalacademy.ezbudgetservice.models.PlanAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanActionRepository extends JpaRepository<PlanAction, Long> {

    PlanAction findAllByPlanActionId(Long id);

}
