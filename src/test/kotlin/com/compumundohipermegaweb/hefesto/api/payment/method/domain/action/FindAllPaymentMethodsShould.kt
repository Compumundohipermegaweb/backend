package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class FindAllPaymentMethodsShould {

    private lateinit var repository: PaymentMethodRepository
    private lateinit var findAllPaymentMethods: FindAllPaymentMethods

    private lateinit var paymentMethodsFound: List<PaymentMethod>

    @Test
    fun `find all payment methods`() {
        givenPaymentMethodRepository()
        givenFindAllPaymentMethods()

        whenFindingAllPaymentMethods()

        thenAllPaymentMethodsWhereFound()
    }

    private fun givenPaymentMethodRepository() {
        repository = mock()
    }

    private fun givenFindAllPaymentMethods() {
        findAllPaymentMethods = FindAllPaymentMethods(repository)
    }

    private fun whenFindingAllPaymentMethods() {
        paymentMethodsFound = findAllPaymentMethods()
    }

    private fun thenAllPaymentMethodsWhereFound() {
        verify(repository).findAllPaymentMethod()
    }
}