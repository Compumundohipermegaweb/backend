package com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodDao

class JpaPaymentMethodRepository (private val springDataPaymentMethodClient: SpringDataPaymentMethodClient) :
    PaymentMethodRepository {

    override fun save(paymentMethod: PaymentMethod): PaymentMethod {
        val paymentMethodDao = PaymentMethodDao(paymentMethod.id,paymentMethod.paymentMethod,paymentMethod.state)
        return springDataPaymentMethodClient.save(paymentMethodDao).toPaymentMethod()
    }

    private fun PaymentMethodDao.toPaymentMethod() = PaymentMethod(id, paymentMethod, state)
}
