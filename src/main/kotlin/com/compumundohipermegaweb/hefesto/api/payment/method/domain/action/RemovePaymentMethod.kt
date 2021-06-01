package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository

class RemovePaymentMethod(private val paymentMethodRepository: PaymentMethodRepository) {

    operator fun invoke(actionData: ActionData) {
        paymentMethodRepository.deleteById(actionData.paymentMethodId)
    }

    data class ActionData(val paymentMethodId: Long)
}