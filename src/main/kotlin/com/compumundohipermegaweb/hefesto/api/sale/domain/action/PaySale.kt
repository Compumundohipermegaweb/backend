package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaySaleRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest

class PaySale(private val cashMovementRepository: CashMovementRepository,
              private val salePaymentRepository: SalePaymentRepository) {

    operator fun invoke(paySaleRequest: PaySaleRequest) {
        val paymentDetails = paySaleRequest.paymentDetails
        val movement = paySaleRequest.cashMovement
        val savedPayment = paymentDetails.map { salePaymentRepository.save(it.toSalePayment(), movement.transactionId) }
        movement.paymentMethodId = savedPayment[0].id
        cashMovementRepository.save(movement, movement.cashStartEndId)
    }

    private fun PaymentRequest.toSalePayment(): SalePayment {
        return SalePayment(0L, type, subTotal)
    }
}




