package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleDetailRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.DefaultSaleService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class DefaultSaleServiceShould {

    private lateinit var saleRepository: SaleRepository
    private lateinit var saleDetailRepository: SaleDetailRepository
    private lateinit var salePaymentRepository: SalePaymentRepository
    private lateinit var saleService: DefaultSaleService
    private lateinit var savedSale: Sale

    @Test
    fun `return the saved sale`() {
        givenSaleItemDetailRepository()
        givenSalePaymentDetailRepository()
        givenSaleRepository()
        givenSaleService()

        whenSavingTheSale()

        thenSaleIsReturned()
    }

    @Test
    fun `save the sale`() {
        givenSaleItemDetailRepository()
        givenSalePaymentDetailRepository()
        givenSaleRepository()
        givenSaleService()

        whenSavingTheSale()

        thenSaleIsSaved()
    }

    @Test
    fun `save the sale details`() {
        givenSaleItemDetailRepository()
        givenSalePaymentDetailRepository()
        givenSaleRepository()
        givenSaleService()

        whenSavingTheSale()

        thenSaleDetailsAreSaved()
    }

    @Test
    fun `save the sale payments`() {
        givenSaleItemDetailRepository()
        givenSalePaymentDetailRepository()
        givenSaleRepository()
        givenSaleService()

        whenSavingTheSale()

        thenSalePaymentsAreSaved()
    }

    private fun givenSaleItemDetailRepository() {
        saleDetailRepository = mock()
        `when`(saleDetailRepository.save(SALE_ITEM_DETAIL[0], 0L)).thenReturn(SALE_ITEM_DETAIL[0])
    }

    private fun givenSalePaymentDetailRepository() {
        salePaymentRepository = mock()
        `when`(salePaymentRepository.save(SALE_PAYMENT_DETAIL[0], 0L)).thenReturn(SALE_PAYMENT_DETAIL[0])
    }

    private fun givenSaleRepository() {
        saleRepository = mock()
        `when`(saleRepository.save(SALE, INVOICE_ID)).thenReturn(SALE)
    }

    private fun givenSaleService() {
        saleService = DefaultSaleService(saleRepository, saleDetailRepository, salePaymentRepository)
    }

    private fun whenSavingTheSale() {
        savedSale = saleService.save(SALE, INVOICE_ID)
    }

    private fun thenSaleIsReturned() {
        then(savedSale).isEqualTo(SALE)
    }

    private fun thenSaleIsSaved() {
        verify(saleRepository).save(SALE, INVOICE_ID)
    }

    private fun thenSaleDetailsAreSaved() {
        verify(saleDetailRepository).saveAll(any(), any())
    }

    private fun thenSalePaymentsAreSaved() {
        verify(salePaymentRepository).saveAll(any(), any())
    }

    private companion object {
        const val INVOICE_ID = 0L
        const val SALE_ID = 0L
        val CLIENT = Client(0L, "", "", "", "", 0.0, "", "", "")
        val SALE_ITEM_DETAIL = listOf(SaleDetail(0L, "", "", 0, 0.0))
        val SALE_PAYMENT_DETAIL = listOf(SalePayment(0L,0L,0L,0L,"","",0.0))
        val SALE = Sale(SALE_ID, "B", CLIENT, 0L, 0L, SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL, null),  0.0, "")
    }
}