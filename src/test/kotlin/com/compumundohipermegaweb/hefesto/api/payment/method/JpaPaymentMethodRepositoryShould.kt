package com.compumundohipermegaweb.hefesto.api.payment.method

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.JpaPaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.PaymentMethodDao
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodRepresentation
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class JpaPaymentMethodRepositoryShould {
    private lateinit var paymentMethodDao: PaymentMethodDao
    private lateinit var paymentMethodRepository: PaymentMethodRepository

    private lateinit var savedPaymentMethod: PaymentMethod
    private lateinit var foundPaymentMethods: List<PaymentMethod>


    @Test
    fun `save the input`(){
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenSavingThePaymentMethod()

        thenThePaymentMethodISuccessfullySaved()
    }

    @Test
    fun `find all payment method`(){
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenFindingThePaymentMethods()

        thenThePaymentMethodISuccessfullyFound()
    }

    @Test
    fun `return all payment method founded`(){
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenFindingThePaymentMethods()

        thenThePaymentMethodISuccessfullyReturner()
    }

    @Test
    fun `delete a payment method by its ID`() {
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenDeletingPaymentMethod()

        thenPaymentMethodHasBeenDeleted()
    }

    private fun paymentMethodDao() {
        paymentMethodDao = mock()
        `when`(paymentMethodDao.save(PAYMENT_METHOD_DAO)).thenReturn(PAYMENT_METHOD_DAO)
        `when`(paymentMethodDao.findAll()).thenReturn(listOf(PAYMENT_METHOD_DAO, ANOTHER_PAYMENT_METHOD_DAO))

    }

    private fun givenPaymentMethodRepository() {
        paymentMethodRepository = JpaPaymentMethodRepository(paymentMethodDao)
    }

    private fun whenSavingThePaymentMethod() {
        savedPaymentMethod = paymentMethodRepository.save(PAYMENT_METHOD)
    }

    private fun whenFindingThePaymentMethods() {
        foundPaymentMethods = paymentMethodRepository.findAllPaymentMethod()
    }

    private fun whenDeletingPaymentMethod() {
        paymentMethodRepository.deleteById(1L)
    }

    private fun thenThePaymentMethodISuccessfullySaved(){
        verify(paymentMethodDao).save(PAYMENT_METHOD_DAO)
        then(savedPaymentMethod).isNotNull
    }

    private fun thenThePaymentMethodISuccessfullyFound(){
        verify(paymentMethodDao).findAll()
    }

    private fun thenThePaymentMethodISuccessfullyReturner(){
        then(foundPaymentMethods).isEqualTo(listOf(PAYMENT_METHOD, ANOTHER_PAYMENT_METHOD))
    }

    private fun thenPaymentMethodHasBeenDeleted() {
        verify(paymentMethodDao).deleteById(1L)
    }

    private companion object{
        val PAYMENT_METHOD_DAO = PaymentMethodRepresentation(0L, "EFECTIVO","EFECTIVO", "ACTIVE")
        val ANOTHER_PAYMENT_METHOD_DAO = PaymentMethodRepresentation(1L,"CUENTA_CORRIENTE", "CUENTA_CORRIENTE", "ACTIVE")
        val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO", "EFECTIVO", "ACTIVE")
        val ANOTHER_PAYMENT_METHOD = PaymentMethod(1L,"CUENTA_CORRIENTE", "CUENTA_CORRIENTE", "ACTIVE")
    }
}