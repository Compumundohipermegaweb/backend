package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class UpdatePaymentMethodShould {

    private lateinit var paymentMethodRepository: PaymentMethodRepository
    private lateinit var updatePaymentMethod: UpdatePaymentMethod

    private lateinit var updatedPaymentMethod: PaymentMethod

    @Test
    fun `update the payment method`() {
        givenPaymentMethodRepository()
        givenUpdatePaymentMethod()

        whenUpdatingPaymentMethod()

        thenPaymentHasBeenSaved()
    }

    private fun givenPaymentMethodRepository() {
        paymentMethodRepository = mock()
    }

    private fun givenUpdatePaymentMethod() {
        updatePaymentMethod = UpdatePaymentMethod(paymentMethodRepository)
    }

    private fun whenUpdatingPaymentMethod() {
        updatedPaymentMethod = updatePaymentMethod(ACTION_DATA)
    }

    private fun thenPaymentHasBeenSaved() {
        verify(paymentMethodRepository).save(PAYMENT_METHOD_TO_SAVE)
    }

    private companion object {
        val ACTION_DATA = UpdatePaymentMethod.ActionData(1L, "ONLINE", "Mercadopago", "ACTIVE")
        val PAYMENT_METHOD_TO_SAVE = PaymentMethod(
                id = ACTION_DATA.id,
                type = ACTION_DATA.type,
                description = ACTION_DATA.description,
                state = ACTION_DATA.state)
    }
}