package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSalePaymentClient
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SalePaymentDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class JpaSalePaymentRepositoryShould {
    private lateinit var springDataSalePaymentClient: SpringDataSalePaymentClient
    private lateinit var salePaymentRepository: SalePaymentRepository
    private lateinit var savedSalePayment: SalePayment

    @Test
    fun `save the input`() {
        givenSalePaymentDetailCrudRepository()
        givenSalePaymentDetailRepository()

        whenSavingTheSaleDetail()

        thenInputSaved()
    }

    private fun givenSalePaymentDetailCrudRepository() {
        springDataSalePaymentClient = Mockito.mock(SpringDataSalePaymentClient::class.java)
        Mockito.`when`(springDataSalePaymentClient.save(SALE_PAYMENT_DETAIL_DAO)).thenReturn(SALE_PAYMENT_DETAIL_DAO)
    }

    private fun givenSalePaymentDetailRepository() {
        salePaymentRepository =JpaSalePaymentRepository(springDataSalePaymentClient)
    }

    private fun whenSavingTheSaleDetail() {
        savedSalePayment = salePaymentRepository.save(SALE_PAYMENT_DETAIL, 0L)
    }

    private fun thenInputSaved() {
        verify(springDataSalePaymentClient).save(SALE_PAYMENT_DETAIL_DAO)
        then(savedSalePayment).isNotNull
    }

    private companion object {
        val SALE_PAYMENT_DETAIL_DAO = SalePaymentDao(0L, 0L, "", 0.0)
        val SALE_PAYMENT_DETAIL = SalePayment(0L, "", 0.0)
    }
}