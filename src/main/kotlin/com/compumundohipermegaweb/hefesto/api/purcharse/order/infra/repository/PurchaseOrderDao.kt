package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation.PurchaseOrderRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface PurchaseOrderDao: CrudRepository<PurchaseOrderRepresentation, Long> {

    fun existsBySku(sku: String): Boolean

    @Modifying
    @Transactional
    @Query("update PurchaseOrderRepresentation po set po.status = :status where po.id = :id")
    fun updateStatusById(id: Long, status: String)

    fun findBySku(sku: String): PurchaseOrderRepresentation?
}
