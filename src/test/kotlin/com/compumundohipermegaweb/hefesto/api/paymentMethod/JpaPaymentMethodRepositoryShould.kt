package com.compumundohipermegaweb.hefesto.api.paymentMethod

import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.repository.JpaPaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.repository.SpringDataPaymentMethodClient
import com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.representation.PaymentMethodDao
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class JpaPaymentMethodRepositoryShould {
    private lateinit var springDataPaymentMethodClient: SpringDataPaymentMethodClient
    private lateinit var paymentMethodRepository: PaymentMethodRepository
    private lateinit var registeredPaymentMethod: PaymentMethod


    @Test
    fun `save the input`(){
        springDataPaymentMethodClient = mock()
        `when`(springDataPaymentMethodClient.save(PAYMENT_METHOD_DAO)).thenReturn(PAYMENT_METHOD_DAO)
        paymentMethodRepository = JpaPaymentMethodRepository(springDataPaymentMethodClient)

        registeredPaymentMethod = paymentMethodRepository.save(PAYMENT_METHOD)
        verify(springDataPaymentMethodClient).save(PAYMENT_METHOD_DAO)
        then(registeredPaymentMethod).isNotNull

    }
    companion object{
        val PAYMENT_METHOD_DAO = PaymentMethodDao(0L,"EFECTIVO","ACTIVE")
        val PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO","ACTIVE")
    }
}