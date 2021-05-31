package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository

class FindAllPaymentMethods(private val paymentMethodsRepository: PaymentMethodRepository) {

    operator fun invoke(): List<PaymentMethod> {
        return paymentMethodsRepository.findAllPaymentMethod()
    }
}