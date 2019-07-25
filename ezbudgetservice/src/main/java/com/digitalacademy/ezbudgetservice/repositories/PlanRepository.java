package com.digitalacademy.ezbudgetservice.repositories;

import com.digitalacademy.ezbudgetservice.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByPlanPartnerIdOrderByPlanStartDateDesc(Long id);
    List<Plan> findAllByPlanPartnerIdOrderByPlanStartDateAsc(Long id);
    Plan findAllByPlanId(Long id);
    //List<Plan> findByPlanStartDateGreaterThanEqualAndPlanEndDateLessThanEqual(Date toDate);

}
