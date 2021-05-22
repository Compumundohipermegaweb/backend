package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService

class RegisterPaymentMethod (private val paymentMethodService: PaymentMethodService){
    operator fun invoke (paymentMethod: PaymentMethod): PaymentMethod {
        return paymentMethodService.savePaymentMethod(paymentMethod)
    }
}

