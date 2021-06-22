package com.compumundohipermegaweb.hefesto.api.invoice

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashStartEnd
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.*
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class DefaultInvoiceServiceShould {
    private lateinit var invoiceRepository: InvoiceRepository
    private lateinit var cashStartEndRepository: CashStartEndRepository
    private lateinit var cashMovementRepository: CashMovementRepository
    private lateinit var invoiceService: InvoiceService

    private lateinit var invoicedSale: Invoice

    @Test
    fun `invoice sale`() {
        givenInvoiceRepository()
        givenCashStartEndRepository()
        givenCashMovementRepository()
        givenInvoiceService()

        whenInvoicingSale()

        thenTheInvoiceIsInvoiced()
    }

    private fun givenInvoiceRepository() {
        invoiceRepository = mock()
        `when`(invoiceRepository.save(any())).thenReturn(INVOICE)
    }

    private fun givenCashStartEndRepository(){
        cashStartEndRepository = mock()
        `when`(cashStartEndRepository.findByCashIdAndEndDate(SALE_REQUEST.branchId)).thenReturn(CASH_START_END)
    }

    private fun givenCashMovementRepository() {
        cashMovementRepository = mock()
        `when`(cashMovementRepository.save(CASH_MOVEMENT, 0L)).thenReturn(CASH_MOVEMENT)
    }

    private fun givenInvoiceService() {
        invoiceService = DefaultInvoiceService(invoiceRepository, cashStartEndRepository, cashMovementRepository)
    }

    private fun whenInvoicingSale() {
        invoicedSale = invoiceService.invoiceSale(SALE, SALE_REQUEST)
    }

    private fun thenTheInvoiceIsInvoiced() {
        verify(cashStartEndRepository).findByCashIdAndEndDate(SALE_REQUEST.branchId)
        verify(cashMovementRepository).save(any(), any())
        verify(invoiceRepository).save(any())
        then(invoicedSale).isNotNull
    }

    private companion object {
        val DATE = Date()
        val CLIENT = Client(0L, "", "", "", "", 0.0, "", "", "")
        val CLIENT_REQUEST = ClientRequest(0L,"", "", "", "", 0.0, "", "", "")
        val SALE_ITEM_DETAIL_REQUEST = listOf(SaleDetailRequest(0L, "",1, 0.0))
        val PAYMENT_METHOD = PaymentMethodRequest(0L,"EFECTIVO","Efectivo")
        val SALE_PAYMENT_DETAIL_REQUEST = listOf(PaymentRequest(PAYMENT_METHOD,0.0,0L,"", ""))
        val SALE_DETAILS_REQUEST = SaleDetailsRequest(SALE_ITEM_DETAIL_REQUEST, SALE_PAYMENT_DETAIL_REQUEST, null)
        val SALE_REQUEST = SaleRequest("A", CLIENT_REQUEST, 0L, 0L, SALE_DETAILS_REQUEST, "LOCAL")
        val SALE_ITEM_DETAIL = listOf(SaleDetail(0L, "","",0, 0.0))
        val SALE_PAYMENT_DETAIL = listOf(SalePayment(0L,0L,0L,0L,"","",0.0))
        val SALE_DETAILS = SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL, null)
        val SALE = Sale(0L, SALE_REQUEST.invoiceType, CLIENT, SALE_REQUEST.salesmanId, SALE_REQUEST.branchId, SALE_DETAILS, 0.0, "LOCAL")
        val INVOICE = Invoice(0L, 0L, "", DATE, "A", CLIENT, 0L, "Domicilio fiscal", "1134567892", "27-28033514-8", "01/01/2021", SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL, null), 0.0, 0.0, 0.0)
        val CASH_START_END = CashStartEnd(0L, 0L, DATE, 0.0, 0L, null, 0.0, 0.0, DATE)
        val CASH_MOVEMENT = CashMovement(0L, 0L, "INGRESO", DATE, 0L, "VENTA", 0L, 0L, 0L, 0.0, "VENTA LOCAL")
    }
}