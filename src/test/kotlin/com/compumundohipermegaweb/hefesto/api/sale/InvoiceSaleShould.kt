package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.ClientRequest
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.ItemDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.PaymentDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.ItemDetailRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.PaymentDetailRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailsRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class InvoiceSaleShould {

    private lateinit var saleService: SaleService
    private lateinit var invoiceService: InvoiceService

    private lateinit var generatedInvoice: Invoice

    private lateinit var invoiceSale: InvoiceSale

    @Test
    fun `generate the invoice of the sale`() {
        givenSaleService()
        givenInvoiceService()
        givenInvoiceSale()

        whenInvoiceSale(TYPE_A_SALE_REQUEST)

        thenTheInvoiceIsGenerated()
    }

    @Test
    fun `save the sale`() {
        givenSaleService()
        givenInvoiceService()
        givenInvoiceSale()

        whenInvoiceSale(TYPE_A_SALE_REQUEST)

        verify(saleService).save(any())
    }

    private fun givenSaleService() {
        saleService = mock()
        `when`(saleService.save(any())).thenReturn(SAVED_SALE_TYPE_A)
    }

    private fun givenInvoiceService() {
        invoiceService = mock()
        `when`(invoiceService.invoiceSale(any())).thenReturn(SAVED_INVOICE_TYPE_A)
    }

    private fun givenInvoiceSale() {
        invoiceSale = InvoiceSale(saleService, invoiceService)
    }

    private fun whenInvoiceSale(saleRequest: SaleRequest) {
        generatedInvoice = invoiceSale(saleRequest)
    }

    private fun thenTheInvoiceIsGenerated() {
        then(generatedInvoice).isNotNull
    }


    private companion object {
        val DEFAULT_CLIENT = Client(0L, "99999999", "Consumidor", "Final", "", "", "", "")
        val CLIENT_REQUEST = ClientRequest("", "", "", "", "", "", "")
        val SALE_ITEM_DETAIL_REQUEST = listOf(ItemDetailRequest(0L, 1, 200.50))
        val SALE_PAYMENT_DETAIL_REQUEST = listOf(PaymentDetailRequest("EFECTIVO",  200.50))
        val SALE_DETAILS_REQUEST = SaleDetailsRequest(SALE_ITEM_DETAIL_REQUEST, SALE_PAYMENT_DETAIL_REQUEST)
        val TYPE_A_SALE_REQUEST = SaleRequest("A", CLIENT_REQUEST, 0L, 0L, SALE_DETAILS_REQUEST, 200.50)
        val SALE_ITEM_DETAIL = listOf(ItemDetail(0L, 1, 200.50))
        val SALE_PAYMENT_DETAIL = listOf(PaymentDetail(0L, "EFECTIVO",200.50))
        val SALE_DETAILS = SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL)
        val SAVED_SALE_TYPE_A = Sale(0L, TYPE_A_SALE_REQUEST.type, 0L, TYPE_A_SALE_REQUEST.idSalesman, TYPE_A_SALE_REQUEST.idBranch, SALE_DETAILS, TYPE_A_SALE_REQUEST.total)
        val SAVED_INVOICE_TYPE_A = Invoice(0L, Date(), SAVED_SALE_TYPE_A.id, SAVED_SALE_TYPE_A.type, DEFAULT_CLIENT, SAVED_SALE_TYPE_A.branchId,"Domicilio fiscal", "1134567892", "27-28033514-8", "01/01/2021", SALE_DETAILS, 200.50, 42.105, 200.50)
    }
}