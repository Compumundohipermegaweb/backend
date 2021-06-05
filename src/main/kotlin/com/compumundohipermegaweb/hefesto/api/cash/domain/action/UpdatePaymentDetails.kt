package com.compumundohipermegaweb.hefesto.api.cash.domain.action

import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentRequest

class UpdatePaymentDetails(private val cashMovementRepository: CashMovementRepository) {

    operator fun invoke(movementId: Long, paymentRequest: List<PaymentRequest>) {
        var movement = cashMovementRepository.findById(movementId)
        if(movement != null) {

        }
    }
}