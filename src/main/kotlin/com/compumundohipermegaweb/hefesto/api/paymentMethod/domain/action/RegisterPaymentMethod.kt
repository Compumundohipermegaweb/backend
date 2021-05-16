package com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.action

import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.repository.PaymentMethodRepository

class RegisterPaymentMethod (private val paymentMethodRepository: PaymentMethodRepository){
    operator fun invoke (paymentMethod: PaymentMethod) = paymentMethodRepository.save(paymentMethod)

}

