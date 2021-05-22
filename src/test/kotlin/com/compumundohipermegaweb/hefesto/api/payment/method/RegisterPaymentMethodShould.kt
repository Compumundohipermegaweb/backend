package com.compumundohipermegaweb.hefesto.api.payment.method


import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.RegisterPaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class RegisterPaymentMethodShould {
    private lateinit var paymentMethodRepository: PaymentMethodRepository
    private lateinit var registerPaymentMethod: RegisterPaymentMethod
    private lateinit var registeredPaymentMethod: PaymentMethod

    @Test
    fun `register a payment method` (){
        givenPaymentMethodRepository()
        givenRegisterPaymentMethod()
        whenRegisteringThePaymentMethod()
        thenThePaymentMethodIsRegisteredSuccessully()

    }
    private fun givenPaymentMethodRepository() {
        paymentMethodRepository = mock()
    }

    private fun givenRegisterPaymentMethod() {
        registerPaymentMethod = RegisterPaymentMethod(paymentMethodRepository)
    }

    private fun whenRegisteringThePaymentMethod() {
        registeredPaymentMethod = registerPaymentMethod(PAYMENT_METHOD)
    }

    private fun thenThePaymentMethodIsRegisteredSuccessully() {
        `when`(paymentMethodRepository.save(PAYMENT_METHOD)).thenReturn(PAYMENT_METHOD)
    }

    companion object{
        val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO","ACTIVO")

    }
}