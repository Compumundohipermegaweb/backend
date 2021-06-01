package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class RemovePaymentMethodShould {

    private lateinit var paymentMethodRepository: PaymentMethodRepository
    private lateinit var removePaymentMethod: RemovePaymentMethod

    @Test
    fun `remove the payment method by its id`() {
        givenPaymentMethodRepository()
        givenRemovePaymentMethod()

        whenRemovingPaymentMethod()

        thenPaymentMethodHasBeenDeleted()
    }

    private fun givenPaymentMethodRepository() {
        paymentMethodRepository = mock()
    }

    private fun givenRemovePaymentMethod() {
        removePaymentMethod = RemovePaymentMethod(paymentMethodRepository)
    }

    private fun whenRemovingPaymentMethod() {
        removePaymentMethod(ACTION_DATA)
    }

    private fun thenPaymentMethodHasBeenDeleted() {
        verify(paymentMethodRepository).deleteById(ACTION_DATA.paymentMethodId)
    }

    private companion object {
        val ACTION_DATA = RemovePaymentMethod.ActionData(1L)
    }
}