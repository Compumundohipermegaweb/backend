package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository

class ConfirmDispatch(private val dispatchRepository: DispatchRepository,
                      private val purchaseOrderRepository: PurchaseOrderRepository) {

    operator fun invoke(dispatchId: Long) {
        dispatchRepository.confirm(dispatchId)
        purchaseOrderRepository.confirmByDispatchId(dispatchId)
    }
}