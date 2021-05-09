package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.PaymentDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.JpaSalePaymentDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.repository.SpringDataSalePaymentDetail
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SalePaymentDetailDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class JpaPaymentDetailRepositoryShould {
    private lateinit var springDataSalePaymentDetail: SpringDataSalePaymentDetail
    private lateinit var salePaymentDetailRepository: SalePaymentDetailRepository
    private lateinit var savedPaymentDetail: PaymentDetail

    @Test
    fun `save the input`() {
        givenSalePaymentDetailCrudRepository()
        givenSalePaymentDetailRepository()

        whenSavingTheSaleDetail()

        thenInputSaved()
    }

    private fun givenSalePaymentDetailCrudRepository() {
        springDataSalePaymentDetail = Mockito.mock(SpringDataSalePaymentDetail::class.java)
        Mockito.`when`(springDataSalePaymentDetail.save(SALE_PAYMENT_DETAIL_DAO)).thenReturn(SALE_PAYMENT_DETAIL_DAO)
    }

    private fun givenSalePaymentDetailRepository() {
        salePaymentDetailRepository =JpaSalePaymentDetailRepository(springDataSalePaymentDetail)
    }

    private fun whenSavingTheSaleDetail() {
        savedPaymentDetail = salePaymentDetailRepository.save(SALE_PAYMENT_DETAIL, 0L)
    }

    private fun thenInputSaved() {
        verify(springDataSalePaymentDetail).save(SALE_PAYMENT_DETAIL_DAO)
        then(savedPaymentDetail).isNotNull
    }

    private companion object {
        val SALE_PAYMENT_DETAIL_DAO = SalePaymentDetailDao(0L, 0L, "", 0.0)
        val SALE_PAYMENT_DETAIL = PaymentDetail(0L, "", 0.0)
    }
}