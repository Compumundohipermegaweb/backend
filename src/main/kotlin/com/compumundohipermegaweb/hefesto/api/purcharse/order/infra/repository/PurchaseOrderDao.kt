package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation.PurchaseOrderRepresentation
import org.springframework.data.repository.CrudRepository

interface PurchaseOrderDao: CrudRepository<PurchaseOrderRepresentation, Long> {
    fun existsBySku(sku: String): Boolean
}
