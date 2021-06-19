package com.compumundohipermegaweb.hefesto.api.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.order.infra.representation.OrderRepresentation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SpringOrderDao: CrudRepository<OrderRepresentation, Long> {

    @Query(value = "SELECT * FROM SALE_ORDER WHERE BRANCH_ID = ?1", nativeQuery = true)
    fun findAllOrdersByBranch(idBranch: Long): List<OrderRepresentation>

}