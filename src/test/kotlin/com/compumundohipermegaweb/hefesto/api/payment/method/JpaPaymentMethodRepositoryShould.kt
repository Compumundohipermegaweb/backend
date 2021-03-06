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
import java.util.*

class JpaPaymentMethodRepositoryShould {
    private lateinit var paymentMethodDao: PaymentMethodDao
    private lateinit var paymentMethodRepository: PaymentMethodRepository

    private lateinit var savedPaymentMethod: PaymentMethod
    private lateinit var foundPaymentMethods: List<PaymentMethod>
    private var foundPaymentMethod: PaymentMethod? = null

    @Test
    fun `save the input`(){
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenSavingThePaymentMethod()

        thenThePaymentMethodISuccessfullySaved()
    }

    @Test
    fun `find all payment method that are not deleted`(){
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenFindingThePaymentMethods()

        thenThePaymentMethodISuccessfullyFound()
    }

    @Test
    fun `logic delete a payment method by its ID`() {
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenDeletingPaymentMethod()

        thenPaymentMethodHasBeenDeleted()
    }

    @Test
    fun `find payment method by id`() {
        paymentMethodDao()
        givenPaymentMethodRepository()

        whenFindingPaymentMethodById()

        thenPaymentMethodHasBeenFoundByID()
    }

    private fun paymentMethodDao() {
        paymentMethodDao = mock()
        `when`(paymentMethodDao.save(PAYMENT_METHOD_DAO)).thenReturn(PAYMENT_METHOD_DAO)
        `when`(paymentMethodDao.findById(0L)).thenReturn(Optional.of(PAYMENT_METHOD_DAO))
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

    private fun whenFindingPaymentMethodById() {
        foundPaymentMethod = paymentMethodRepository.findById(0L)
    }

    private fun thenThePaymentMethodISuccessfullySaved(){
        verify(paymentMethodDao).save(PAYMENT_METHOD_DAO)
        then(savedPaymentMethod).isNotNull
    }

    private fun thenThePaymentMethodISuccessfullyFound(){
        verify(paymentMethodDao).findAllByDeleted(false)
    }

    private fun thenPaymentMethodHasBeenDeleted() {
        verify(paymentMethodDao).updateDeletedById(1L)
    }

    private fun thenPaymentMethodHasBeenFoundByID() {
        verify(paymentMethodDao).findById(0L)
        then(foundPaymentMethod).isEqualTo(PAYMENT_METHOD)
    }

    private companion object{
        val PAYMENT_METHOD_DAO = PaymentMethodRepresentation(0L, "EFECTIVO","EFECTIVO", "ACTIVE", false)
        val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO", "EFECTIVO", "ACTIVE")
    }
}