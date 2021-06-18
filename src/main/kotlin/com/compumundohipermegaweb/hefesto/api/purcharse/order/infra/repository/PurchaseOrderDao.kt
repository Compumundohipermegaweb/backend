package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation.PurchaseOrderRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface PurchaseOrderDao: CrudRepository<PurchaseOrderRepresentation, Long> {

    fun existsBySkuAndStatus(sku: String, status: String): Boolean

    @Modifying
    @Transactional
    @Query("update PurchaseOrderRepresentation po set po.status = :status where po.id = :id")
    fun updateStatus(id: Long, status: String)

    @Modifying
    @Transactional
    @Query("update PurchaseOrderRepresentation po set po.dispatchId = :dispatchId where po.id = :id")
    fun updateDispatchId(id: Long, dispatchId: Long)

    fun findBySku(sku: String): PurchaseOrderRepresentation?

    fun findAllByDispatchId(dispatchId: Long): List<PurchaseOrderRepresentation>

    @Modifying
    @Transactional
    @Query("update PurchaseOrderRepresentation po set po.status = :status where po.dispatchId = :dispatchId")
    fun updateStatusByDispatchId(dispatchId: Long, status: String)

    @Modifying
    @Transactional
    @Query("update PurchaseOrderRepresentation po set po.dispatched = :dispatchedAmount where po.id = :id")
    fun updateDispatchedAmount(id: Long, dispatchedAmount: Int)

    @Modifying
    @Transactional
    @Query("update PurchaseOrderRepresentation po set po.cost = :cost where po.id = :id")
    fun updateCost(id: Long, cost: Double)
}
