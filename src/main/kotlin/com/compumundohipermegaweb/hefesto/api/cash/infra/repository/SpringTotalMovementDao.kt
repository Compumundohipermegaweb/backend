package com.compumundohipermegaweb.hefesto.api.cash.infra.repository

import com.compumundohipermegaweb.hefesto.api.cash.infra.representation.TotalMovementRepresentation

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringTotalMovementDao: CrudRepository<TotalMovementRepresentation, Long> {
    @Query(
        nativeQuery = true,
        value =  " SELECT row_number() OVER (ORDER BY 1) ID, BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD  ,\"SOURCE\", CARD, DIGITS, DETAIL, TOTAL, ORDEN \"LEVEL\" "
                + " FROM  "
                + " ( "
                + " 	SELECT BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD  ,\"SOURCE\", CARD, DIGITS, DETAIL, TOTAL , 4 ORDEN "
                + " 	FROM VW_MOVEMENTS_DETAIL "
                + " UNION "
                + " 	SELECT  BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD  ,\"SOURCE\", CARD ,'Total' DIGITS,null DETAIL, SUM(TOTAL) TOTAL, 3 ORDEN "
                + " 	FROM VW_MOVEMENTS_DETAIL  "
                + " 	WHERE CARD is not null "
                + " 	GROUP by  BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD ,\"SOURCE\", CARD "
                + " UNION "
                + " 	SELECT  BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD  ,\"SOURCE\", 'Total' CARD, null DIGITS,null DETAIL,SUM(TOTAL) TOTAL, 2 ORDEN "
                + " 	FROM VW_MOVEMENTS_DETAIL  "
                + " 	GROUP by  BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD ,\"SOURCE\" "
                + " UNION "
                + " 	SELECT BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD ,'Total' \"SOURCE\", null CARD,null DIGITS,null DETAIL,SUM(TOTAL) TOTAL, 1 ORDEN "
                + " 	FROM VW_MOVEMENTS_DETAIL  "
                + " 	GROUP BY BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID ,PAYMENT_METHOD "
                + " ) as A "
                + " WHERE BRANCH_ID= ?1"
                + " ORDER BY BRANCH_ID ,CASH_ID ,DATE ,CASH_START_END_ID ,MOVEMENT_ID,PAYMENT_METHOD, "
                + " CASE WHEN \"SOURCE\"= 'Total' THEN 'Z' ELSE \"SOURCE\" END  ,CASE WHEN \"SOURCE\"= 'Total' THEN 1 ELSE 0 END, CASE WHEN CARD = 'Total'  THEN 'Z' ELSE CARD END, ORDEN DESC ")
           //value = "SELECT * FROM VW_MOVEMENTS_DETAIL WHERE BRANCH_ID= ?1")
    fun findAll(branchId: Long): List<TotalMovementRepresentation>
}