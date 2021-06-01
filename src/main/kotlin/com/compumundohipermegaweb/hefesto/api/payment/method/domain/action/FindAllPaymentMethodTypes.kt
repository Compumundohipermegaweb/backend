package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod

class FindAllPaymentMethodTypes {

    operator fun invoke(): List<PaymentMethod.Type> {
        return PaymentMethod.Type.values().toList()
    }
}