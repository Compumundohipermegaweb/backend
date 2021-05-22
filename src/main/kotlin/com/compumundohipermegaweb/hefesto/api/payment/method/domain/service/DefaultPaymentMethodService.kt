package com.compumundohipermegaweb.hefesto.api.payment.method.domain.service

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository

class DefaultPaymentMethodService(private val paymentMethodRepository: PaymentMethodRepository): PaymentMethodService {
    override fun savePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod {
        return paymentMethodRepository.save(paymentMethod)
    }

    override fun findAllPaymentMethod(): List<PaymentMethod> {
        return paymentMethodRepository.findAllPaymentMethod()
    }
}