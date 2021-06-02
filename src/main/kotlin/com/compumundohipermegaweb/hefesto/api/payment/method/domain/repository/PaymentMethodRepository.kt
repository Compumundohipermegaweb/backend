package com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod

interface PaymentMethodRepository {
    fun save (paymentMethod : PaymentMethod) : PaymentMethod
    fun findAllPaymentMethod(): List<PaymentMethod>
    fun deleteById(id: Long)
    fun findById(id: Long): PaymentMethod?
}
