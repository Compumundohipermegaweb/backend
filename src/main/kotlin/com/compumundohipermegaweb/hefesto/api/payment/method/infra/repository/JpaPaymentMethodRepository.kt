package com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodRepresentation

class JpaPaymentMethodRepository (private val paymentMethodDao: PaymentMethodDao): PaymentMethodRepository {

    override fun save(paymentMethod: PaymentMethod): PaymentMethod {
        return paymentMethodDao.save(paymentMethod.toPaymentMethodDao()).toPaymentMethod()
    }

    override fun findAllPaymentMethod(): List<PaymentMethod> {
        return paymentMethodDao.findAllByDeleted(false).map { it.toPaymentMethod() }
    }

    override fun deleteById(id: Long) {
        paymentMethodDao.updateDeletedById(id)
    }

    override fun findById(id: Long): PaymentMethod? {
        val paymentMethod = paymentMethodDao.findById(id)
        if(paymentMethod.isPresent) {
            return paymentMethod.get().toPaymentMethod()
        }
        return null
    }

    private fun PaymentMethod.toPaymentMethodDao(): PaymentMethodRepresentation {
        return PaymentMethodRepresentation(id, type, description, state, false)
    }

    private fun PaymentMethodRepresentation.toPaymentMethod(): PaymentMethod {
        return PaymentMethod(id, type, description, state)
    }
}


