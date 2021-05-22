package com.compumundohipermegaweb.hefesto.api.payment.method

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.DefaultPaymentMethodService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class DefaultPaymentMethodServiceShould {
    private lateinit var paymentMethodRepository: PaymentMethodRepository
    private lateinit var paymentMethodService: DefaultPaymentMethodService

    private lateinit var registeredPaymentMethod: PaymentMethod
    private lateinit var foundPaymentMethods: List<PaymentMethod>


    @Test
    fun `save the input`(){
        givenPaymentMethodRepositoryClient()
        givenPaymentMethodService()

        whenSavingThePaymentMethod()

        thenThePaymentMethodISuccessfullySaved()
    }

    @Test
    fun `find all payment method`(){
        givenPaymentMethodRepositoryClient()
        givenPaymentMethodService()

        whenFindingThePaymentMethods()

        thenThePaymentMethodISuccessfullyFound()
    }

    @Test
    fun `return all payment method founded`(){
        givenPaymentMethodRepositoryClient()
        givenPaymentMethodService()

        whenFindingThePaymentMethods()

        thenThePaymentMethodISuccessfullyReturner()
    }

    private fun givenPaymentMethodRepositoryClient() {
        paymentMethodRepository = mock()
        `when`(paymentMethodRepository.save(PAYMENT_METHOD)).thenReturn(PAYMENT_METHOD)
        `when`(paymentMethodRepository.findAllPaymentMethod()).thenReturn(listOf(PAYMENT_METHOD, ANOTHER_PAYMENT_METHOD))

    }

    private fun givenPaymentMethodService() {
        paymentMethodService = DefaultPaymentMethodService(paymentMethodRepository)
    }

    private fun whenSavingThePaymentMethod() {
        registeredPaymentMethod = paymentMethodService.savePaymentMethod(PAYMENT_METHOD)
    }

    private fun whenFindingThePaymentMethods() {
        foundPaymentMethods = paymentMethodService.findAllPaymentMethod()
    }

    private fun thenThePaymentMethodISuccessfullySaved(){
        verify(paymentMethodRepository).save(PAYMENT_METHOD)
        then(registeredPaymentMethod).isNotNull
    }

    private fun thenThePaymentMethodISuccessfullyFound(){
        verify(paymentMethodRepository).findAllPaymentMethod()
    }

    private fun thenThePaymentMethodISuccessfullyReturner(){
        then(foundPaymentMethods).isEqualTo(listOf(PAYMENT_METHOD, ANOTHER_PAYMENT_METHOD))
    }


    companion object{
        val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO","ACTIVE")
        val ANOTHER_PAYMENT_METHOD = PaymentMethod(1L,"CUENTA CORRIENTE","ACTIVE")
    }
}