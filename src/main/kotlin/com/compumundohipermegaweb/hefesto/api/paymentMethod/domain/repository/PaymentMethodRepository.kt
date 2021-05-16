package com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.repository

import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.model.PaymentMethod

interface PaymentMethodRepository {
    fun save (paymentMethod : PaymentMethod) : PaymentMethod
}
