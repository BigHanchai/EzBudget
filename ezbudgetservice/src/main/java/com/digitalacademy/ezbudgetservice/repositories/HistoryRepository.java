package com.digitalacademy.ezbudgetservice.repositories;

import com.digitalacademy.ezbudgetservice.models.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByHistoryPlanIdAndHistoryPlanPartnerIdOrderByHistoryLastUpdateDesc(Long planID, Long partnerID);

    @Query(value = "SELECT History.history_plan_action_id FROM History WHERE History.history_plan_id = :planID AND History.history_plan_partner_id = :partnerID \n" +
            "Group By History.history_plan_action_id \n" +
            "Order By History.history_plan_action_id ASC;", nativeQuery = true)
    ArrayList<Long> selectActionGroupByActionASC(@Param("planID")Long planID, @Param("partnerID")Long partnerID);

    @Query(value = "SELECT sum(History.history_balance) FROM History WHERE History.history_plan_id = :planID AND History.history_plan_partner_id = :partnerID", nativeQuery = true)
    Double sumAmountByHistoryBalance(@Param("planID")Long planID, @Param("partnerID")Long partnerID);

    @Query(value = "SELECT sum(History.history_balance) FROM History WHERE History.history_plan_id = :planID AND History.history_plan_partner_id = :partnerID  AND History.history_plan_action_id = :actionID", nativeQuery = true)
    Double sumAmountByHistoryBalanceByAction(@Param("planID")Long planID, @Param("partnerID")Long partnerID, @Param("actionID")Long actionID);

    @Query(value = "SELECT sum(History.history_balance) FROM History WHERE History.history_plan_id = :planID AND History.history_plan_partner_id = :partnerID AND History.history_last_update BETWEEN :dateStart AND :dateEnd", nativeQuery = true)
    Double selectHistoryWithDate(@Param("planID")Long planID, @Param("partnerID")Long partnerID,@Param("dateStart")String dateStart,@Param("dateEnd")String dateEnd);

}
