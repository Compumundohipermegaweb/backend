package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository

class RegisterPaymentMethod (private val paymentMethodRepository: PaymentMethodRepository){
    operator fun invoke (paymentMethod: PaymentMethod) = paymentMethodRepository.save(paymentMethod)

}

