package com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodDao

class JpaPaymentMethodRepository (private val springDataPaymentMethodClient: SpringDataPaymentMethodClient) :
    PaymentMethodRepository {

    override fun save(paymentMethod: PaymentMethod): PaymentMethod {
        return springDataPaymentMethodClient.save(paymentMethod.toPaymentMethodDao()).toPaymentMethod()
    }

    override fun findAllPaymentMethod(): List<PaymentMethod> {
        return springDataPaymentMethodClient.findAll().map { it.toPaymentMethod() }
    }

    private fun PaymentMethod.toPaymentMethodDao(): PaymentMethodDao {
        return PaymentMethodDao(id, description, state)
    }

    private fun PaymentMethodDao.toPaymentMethod(): PaymentMethod {
        return PaymentMethod(id, description, state)
    }
}


