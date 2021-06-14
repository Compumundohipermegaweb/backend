package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.Dispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository

class FindAllDispatches(private val dispatchRepository: DispatchRepository,
                        private val supplierRepository: SupplierRepository,
                        private val purchaseOrderRepository: PurchaseOrderRepository) {


    operator fun invoke(): List<FullDispatch> {
        val dispatches = dispatchRepository.findAll()
        val fullDispatches = mutableListOf<FullDispatch>()

        dispatches.forEach {
            val supplier = supplierRepository.findById(it.supplierId)!!
            val purchaseOrders = purchaseOrderRepository.findByDispatchId(it.id)
            val fullDispatch = FullDispatch(it.id, supplier, it.totalCost, it.status, purchaseOrders)
            fullDispatches.add(fullDispatch)
        }

        return fullDispatches
    }

}

data class FullDispatch(val id: Long,
                        val supplier: Supplier,
                        val totalCost: Double,
                        val status: Dispatch.Status,
                        val purchaseOrders: List<PurchaseOrder>)