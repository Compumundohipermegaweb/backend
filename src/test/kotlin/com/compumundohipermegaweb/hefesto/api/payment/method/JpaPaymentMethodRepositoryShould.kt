package com.compumundohipermegaweb.hefesto.api.payment.method

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.JpaPaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.SpringDataPaymentMethodClient
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodDao
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class JpaPaymentMethodRepositoryShould {
    private lateinit var springDataPaymentMethodClient: SpringDataPaymentMethodClient
    private lateinit var paymentMethodRepository: PaymentMethodRepository

    private lateinit var savedPaymentMethod: PaymentMethod
    private lateinit var foundPaymentMethods: List<PaymentMethod>


    @Test
    fun `save the input`(){
        givenSpringPaymentMethodClient()
        givenPaymentMethodRepository()

        whenSavingThePaymentMethod()

        thenThePaymentMethodISuccessfullySaved()
    }

    @Test
    fun `find all payment method`(){
        givenSpringPaymentMethodClient()
        givenPaymentMethodRepository()

        whenFindingThePaymentMethods()

        thenThePaymentMethodISuccessfullyFound()
    }

    @Test
    fun `return all payment method founded`(){
        givenSpringPaymentMethodClient()
        givenPaymentMethodRepository()

        whenFindingThePaymentMethods()

        thenThePaymentMethodISuccessfullyReturner()
    }

    private fun givenSpringPaymentMethodClient() {
        springDataPaymentMethodClient = mock()
        `when`(springDataPaymentMethodClient.save(PAYMENT_METHOD_DAO)).thenReturn(PAYMENT_METHOD_DAO)
        `when`(springDataPaymentMethodClient.findAll()).thenReturn(listOf(PAYMENT_METHOD_DAO, ANOTHER_PAYMENT_METHOD_DAO))

    }

    private fun givenPaymentMethodRepository() {
        paymentMethodRepository = JpaPaymentMethodRepository(springDataPaymentMethodClient)
    }

    private fun whenSavingThePaymentMethod() {
        savedPaymentMethod = paymentMethodRepository.save(PAYMENT_METHOD)
    }

    private fun whenFindingThePaymentMethods() {
        foundPaymentMethods = paymentMethodRepository.findAllPaymentMethod()
    }

    private fun thenThePaymentMethodISuccessfullySaved(){
        verify(springDataPaymentMethodClient).save(PAYMENT_METHOD_DAO)
        then(savedPaymentMethod).isNotNull
    }

    private fun thenThePaymentMethodISuccessfullyFound(){
        verify(springDataPaymentMethodClient).findAll()
    }

    private fun thenThePaymentMethodISuccessfullyReturner(){
        then(foundPaymentMethods).isEqualTo(listOf(PAYMENT_METHOD, ANOTHER_PAYMENT_METHOD))
    }


    private companion object{
        val PAYMENT_METHOD_DAO = PaymentMethodDao(0L,"EFECTIVO","ACTIVE")
        val ANOTHER_PAYMENT_METHOD_DAO = PaymentMethodDao(1L,"CUENTA CORRIENTE","ACTIVE")
        val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO","ACTIVE")
        val ANOTHER_PAYMENT_METHOD = PaymentMethod(1L,"CUENTA CORRIENTE","ACTIVE")
    }
}