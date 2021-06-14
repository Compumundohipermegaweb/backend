package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.item.domain.repository.ItemRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.Dispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchError
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchOrdersResult
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.DispatchedItem
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PriceToleranceRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.repository.SupplierRepository

class DispatchOrders(private val dispatchRepository: DispatchRepository,
                     private val supplierRepository: SupplierRepository,
                     private val itemRepository: ItemRepository,
                     private val purchaseOrderRepository: PurchaseOrderRepository,
                     private val priceToleranceRepository: PriceToleranceRepository) {

    operator fun invoke(actionData: ActionData): DispatchOrdersResult {
        val result = DispatchOrdersResult(Dispatch.Status.ACCEPTED, mutableListOf())
        val dispatch = Dispatch(actionData.dispatchId, actionData.supplierId, actionData.totalCost, Dispatch.Status.ACCEPTED)
        if(supplierDoesNotExists(actionData.supplierId)) {
            saveRejection(actionData)
            result.setStatus(Dispatch.Status.REJECTED_UNKNOWN_SUPPLIER)
            return result
        }

        actionData.dispatchedItems.forEach {
            if(itemDoesNotExists(it.sku)) {
                result.errors.add(DispatchError(DispatchError.Code.UNKNOWN_SKU, it))
            } else if(!purchaseOrderRepository.exists(it.sku)) {
                result.errors.add(DispatchError(DispatchError.Code.NO_PURCHASE_ORDER, it))
            } else if(priceDifferenceIsTooHigh(it.sku, it.unitPrice)) {
                result.errors.add(DispatchError(DispatchError.Code.PRICE_DIFFERENCE_TOO_HIGH, it))
            } else {
                val purchaseOrder = purchaseOrderRepository.findBySku(it.sku)!!
                purchaseOrderRepository.accept(purchaseOrder.id, dispatch.id)
            }
        }

        dispatchRepository.save(dispatch)
        return result
    }

    private fun saveRejection(actionData: ActionData) {
        dispatchRepository.save(Dispatch(actionData.dispatchId, actionData.supplierId, actionData.totalCost, Dispatch.Status.REJECTED_UNKNOWN_SUPPLIER))
    }

    private fun itemDoesNotExists(sku: String) = itemRepository.findBySku(sku) == null

    private fun supplierDoesNotExists(supplierId: Long) = supplierRepository.findById(supplierId) == null

    private fun priceDifferenceIsTooHigh(sku: String, unitPrice: Double): Boolean {
        val item = itemRepository.findBySku(sku)!!
        val allowedDifference = priceToleranceRepository.find()
        val tolerance = (allowedDifference * item.cost) / 100

        return (unitPrice - item.cost) > tolerance
    }

    private fun DispatchOrdersResult.setStatus(dispatchStatus: Dispatch.Status) {
        status = dispatchStatus
    }
    data class ActionData(val dispatchId: Long, val supplierId: Long, val totalCost: Double, val dispatchedItems: List<DispatchedItem>)
}
