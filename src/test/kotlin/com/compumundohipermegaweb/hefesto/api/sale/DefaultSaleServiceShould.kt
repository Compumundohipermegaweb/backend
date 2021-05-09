package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.ItemDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.PaymentDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.DefaultSaleService
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DefaultSaleServiceShould {

    private lateinit var saleRepository: SaleRepository
    private lateinit var saleItemDetailRepository: SaleItemDetailRepository
    private lateinit var salePaymentDetailRepository: SalePaymentDetailRepository
    private lateinit var saleService: DefaultSaleService
    private lateinit var resultado: Sale

    @Test
    fun `return a sale object`() {
        givenSaleItemDetailRepository()
        givenSalePaymentDetailRepository()
        givenSaleRepository()
        givenSaleService()

        whenSavingTheResult()

        thenResultIsTheExpectedOne()
    }

    private fun givenSaleItemDetailRepository() {
        saleItemDetailRepository = mock(SaleItemDetailRepository::class.java)
        `when`(saleItemDetailRepository.save(SALE_ITEM_DETAIL[0], 0L)).thenReturn(SALE_ITEM_DETAIL[0])
    }

    private fun givenSalePaymentDetailRepository() {
        salePaymentDetailRepository = mock(SalePaymentDetailRepository::class.java)
        `when`(salePaymentDetailRepository.save(SALE_PAYMENT_DETAIL[0], 0L)).thenReturn(SALE_PAYMENT_DETAIL[0])
    }

    private fun givenSaleRepository() {
        saleRepository = mock(SaleRepository::class.java)
        `when`(saleRepository.save(SALE)).thenReturn(SALE)
    }

    private fun givenSaleService() {
        saleService = DefaultSaleService(saleRepository, saleItemDetailRepository, salePaymentDetailRepository)
    }

    private fun whenSavingTheResult() {
        resultado = saleService.save(SALE)
    }

    private fun thenResultIsTheExpectedOne() {
        verify(saleItemDetailRepository).save(SALE_ITEM_DETAIL[0], 0L)
        verify(salePaymentDetailRepository).save(SALE_PAYMENT_DETAIL[0], 0L)
        verify(saleRepository).save(SALE)
        then(resultado).isEqualTo(SALE)
    }

    private companion object {
        val SALE_ITEM_DETAIL = listOf(ItemDetail(0L, 0, 0.0))
        val SALE_PAYMENT_DETAIL = listOf(PaymentDetail(0L, "EFECTIVO", 0.0))
        val SALE = Sale(0L, "B", 0L, 0L, 0L, SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL),  0.0)
    }
}