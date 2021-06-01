package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository

class UpdatePaymentMethod(private val paymentMethodRepository: PaymentMethodRepository) {

    operator fun invoke(actionData: ActionData): PaymentMethod {
        val paymentMethod = actionData.toPaymentMethod()
        return paymentMethodRepository.save(paymentMethod)
    }

    private fun ActionData.toPaymentMethod() = PaymentMethod(id, type, description, state)

    data class ActionData(val id: Long, val type: String, val description: String, val state: String)
}