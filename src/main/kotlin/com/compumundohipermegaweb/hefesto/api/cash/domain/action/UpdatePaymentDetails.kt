package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest

class UpdatePaymentDetails(private val cashMovementRepository: CashMovementRepository,
                           private val salePaymentRepository: SalePaymentRepository,
) {

    operator fun invoke(movementId: Long, paymentRequest: List<PaymentRequest>) {
        val movement = cashMovementRepository.findById(movementId)
        if(movement != null) {
            if(movement.sourceDescription == "VENTA") {
                val payments = salePaymentRepository.findBySaleId(movement.sourceId)
                payments.forEach { salePaymentRepository.delete(it, movement.sourceId) }
                paymentRequest.forEach { salePaymentRepository.save(it.toSalePayment(), movement.sourceId) }
            }
        }
    }

    private fun PaymentRequest.toSalePayment(): SalePayment {
        return SalePayment(0L, type, subTotal)
    }
}


