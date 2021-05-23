package com.compumundohipermegaweb.hefesto.api.payment.method


import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.RegisterPaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class RegisterPaymentMethodShould {
    private lateinit var paymentMethodService: PaymentMethodService
    private lateinit var registerPaymentMethod: RegisterPaymentMethod
    private lateinit var registeredPaymentMethod: PaymentMethod

    @Test
    fun `register a payment method` (){
        givenPaymentMethodRepository()
        givenRegisterPaymentMethod()

        whenRegisteringThePaymentMethod()

        thenThePaymentMethodIsRegisteredSuccessfully()
    }

    @Test
    fun `return the registered payment method` (){
        givenPaymentMethodRepository()
        givenRegisterPaymentMethod()

        whenRegisteringThePaymentMethod()

        thenThePaymentMethodRegisteredIsReturned()
    }

    private fun givenPaymentMethodRepository() {
        paymentMethodService = mock()
        `when`(paymentMethodService.savePaymentMethod(PAYMENT_METHOD)).thenReturn(PAYMENT_METHOD)
    }

    private fun givenRegisterPaymentMethod() {
        registerPaymentMethod = RegisterPaymentMethod(paymentMethodService)
    }

    private fun whenRegisteringThePaymentMethod() {
        registeredPaymentMethod = registerPaymentMethod(PAYMENT_METHOD)
    }

    private fun thenThePaymentMethodIsRegisteredSuccessfully() {
        verify(paymentMethodService).savePaymentMethod(PAYMENT_METHOD)
        then(registerPaymentMethod).isNotNull
    }

    private fun thenThePaymentMethodRegisteredIsReturned() {
        then(registeredPaymentMethod).isEqualTo(PAYMENT_METHOD)
    }

    private companion object{
        val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO", "EFECTIVO", "ACTIVO")
    }
}