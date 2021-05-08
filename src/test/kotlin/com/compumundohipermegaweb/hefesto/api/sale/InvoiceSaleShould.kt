package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.client.rest.ClientRequest
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailsRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

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

        whenInvoiceSale(TYPE_B_SALE_REQUEST)

        thenTheInvoiceIsGenerated()
    }

    @Test
    fun `calculate the iva for A type sales`() {
        givenSaleService()
        givenInvoiceService()
        givenInvoiceSale()

        whenInvoiceSale(TYPE_A_SALE_REQUEST)

        then(generatedInvoice.ivaSubTotal).isEqualTo(EXPECTED_IVA)
    }

    private fun givenSaleService() {
        saleService = mock()
        `when`(saleService.save(any())).thenReturn(SAVED_SALE)
    }

    private fun givenInvoiceService() {
        invoiceService = mock()
        `when`(invoiceService.save(any())).thenReturn(SAVED_INVOICE)
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
        val CLIENT_REQUEST = ClientRequest("", "", "", "", "", "", "")
        val SALE_DETAIL_REQUEST = listOf(SaleDetailRequest(0L, 1, 200.50))
        val SALE_DETAILS_REQUEST = SaleDetailsRequest(SALE_DETAIL_REQUEST)
        val TYPE_B_SALE_REQUEST = SaleRequest("B", CLIENT_REQUEST, 0L, 0L, SALE_DETAILS_REQUEST, 200.50)
        val TYPE_A_SALE_REQUEST = SaleRequest("A", CLIENT_REQUEST, 0L, 0L, SALE_DETAILS_REQUEST, 200.50)
        const val EXPECTED_IVA = 42.105
        val SALE_DETAIL = listOf(SaleDetail(0L, 1, 200.50))
        val SALE_DETAILS = SaleDetails(SALE_DETAIL)
        val SAVED_SALE = Sale(0L, TYPE_B_SALE_REQUEST.type, 0L, TYPE_B_SALE_REQUEST.idSalesman, TYPE_B_SALE_REQUEST.idBranch, SALE_DETAILS, TYPE_B_SALE_REQUEST.total)
        val SAVED_INVOICE = Invoice(0L, SAVED_SALE.id, TYPE_B_SALE_REQUEST.type, TYPE_B_SALE_REQUEST.idBranch, 200.50, 42.105, 200.50)
    }
}