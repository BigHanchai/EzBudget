package com.digitalacademy.ezbudgetservice.repositories;

import com.digitalacademy.ezbudgetservice.models.PlanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanDetailsRepository extends JpaRepository<PlanDetails, Long> {

    List<PlanDetails> findAllByPlanDetailsPlanIdAndPlanDetailsPlanPartnerId(Long planID, Long partnerID);


}
