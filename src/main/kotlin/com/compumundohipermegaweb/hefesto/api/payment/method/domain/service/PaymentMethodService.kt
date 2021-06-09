package com.compumundohipermegaweb.hefesto.api.payment.method.domain.service

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod

interface PaymentMethodService {
    fun savePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod
    fun findAllPaymentMethod(): List<PaymentMethod>
    fun findById(id: Long):PaymentMethod?
}