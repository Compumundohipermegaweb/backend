package com.compumundohipermegaweb.hefesto.api.online.sale

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.online.sale.domain.action.ProcessOnlineSale
import com.compumundohipermegaweb.hefesto.api.payment.method.rest.request.PutPaymentMethodRequest
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedSale
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service.RejectedSaleService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.*
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class ProcessOnlineSaleShould {
    private lateinit var itemService: ItemService
    private lateinit var clientService: ClientService
    private lateinit var stockService: StockService
    private lateinit var rejectedSaleService: RejectedSaleService

    private lateinit var invoiceSale: InvoiceSale
    private lateinit var processOnlineSale: ProcessOnlineSale

    @Test
    fun `process complete online sale`() {
        givenItemService()
        givenClientService()
        givenStockService()
        givenRejectedSaleService()
        givenInvoiceSale()
        givenProcessOnlineSale()

        whenProcessingTheOnlineSale()

        thenTheOnlineSaleIsFullyProcessed()
    }

    @Test
    fun `process partial reject online sale`() {
        givenItemService()
        givenClientService()
        givenStockService()
        givenRejectedSaleService()
        givenInvoiceSale()
        givenProcessOnlineSale()

        whenProcessingThePartialOnlineSale()

        thenTheOnlineSaleIsPartiallyProcessed()
    }

    @Test
    fun `process total reject online sale`() {
        givenItemService()
        givenClientService()
        givenStockService()
        givenRejectedSaleService()
        givenInvoiceSale()
        givenProcessOnlineSale()

        whenProcessingTheOnlineSaleToTotallyReject()

        thenTheOnlineSaleIsTotallyRejected()
    }

    private fun givenClientService() {
        clientService = mock()
        `when`(clientService.findByDocument(CLIENT.documentNumber)).thenReturn(CLIENT)
    }

    private fun givenItemService() {
        itemService = mock()
        `when`(itemService.findItemById(SALE_REQUEST_TO_PROCESS.saleDetailsRequest.detailsRequest[0].id)).thenReturn(ITEM_UNO)
        `when`(itemService.findItemById(SALE_REQUEST_TO_PROCESS.saleDetailsRequest.detailsRequest[1].id)).thenReturn(ITEM_DOS)
        `when`(itemService.findItemById(3L)).thenReturn(ITEM_TRES)
        `when`(itemService.findItemById(4L)).thenReturn(ITEM_CUATRO)
    }

    private fun givenStockService() {
        stockService = mock()
        `when`(stockService.findBySku(ITEM_UNO.sku)).thenReturn(STOCK_UNO)
        `when`(stockService.findBySku(ITEM_DOS.sku)).thenReturn(STOCK_DOS)
        `when`(stockService.findBySku(ITEM_TRES.sku)).thenReturn(STOCK_TRES)
        `when`(stockService.findBySku(ITEM_CUATRO.sku)).thenReturn(STOCK_CUATRO)
    }

    private fun givenInvoiceSale() {
        invoiceSale = mock()
        `when`(invoiceSale.invoke(SALE_REQUEST_TO_PROCESS)).thenReturn(INVOICE_UNO)
        `when`(invoiceSale.invoke(SALE_REQUEST_TO_PROCESS_PARTIALLY)).thenReturn(INVOICE_DOS)
    }

    private fun givenRejectedSaleService() {
        rejectedSaleService = mock()
        `when`(rejectedSaleService.saveRejectedSale(REJECTED_SALE_UNO, listOf(REJECTED_ITEM_DETAIL_UNO))).thenReturn(REJECTED_SALE_UNO)
        `when`(rejectedSaleService.saveRejectedSale(REJECTED_SALE_DOS, listOf(REJECTED_ITEM_DETAIL_DOS, REJECTED_ITEM_DETAIL_UNO))).thenReturn(REJECTED_SALE_DOS)
    }

    private fun givenProcessOnlineSale() {
        processOnlineSale = ProcessOnlineSale(invoiceSale, stockService, itemService, clientService, rejectedSaleService)
    }

    private fun whenProcessingTheOnlineSale() {
        processOnlineSale(SALE_REQUEST_TO_PROCESS)
    }

    private fun whenProcessingThePartialOnlineSale() {
        processOnlineSale(SALE_REQUEST_TO_PROCESS_PARTIALLY)
    }

    private fun whenProcessingTheOnlineSaleToTotallyReject() {
        processOnlineSale(SALE_REQUEST_TO_REJECT_TOTALLY)
    }

    private fun thenTheOnlineSaleIsFullyProcessed() {
        verify(clientService).findByDocument(CLIENT.documentNumber)
        verify(itemService).findItemById(ITEM_UNO.id)
        verify(itemService).findItemById(ITEM_DOS.id)
        verify(stockService).findBySku(ITEM_UNO.sku)
        verify(stockService).findBySku(ITEM_DOS.sku)
        verify(invoiceSale).invoke(SALE_REQUEST_TO_PROCESS)
    }

    private fun thenTheOnlineSaleIsPartiallyProcessed() {
        verify(clientService).findByDocument(CLIENT.documentNumber)
        verify(itemService).findItemById(ITEM_UNO.id)
        verify(itemService).findItemById(ITEM_TRES.id)
        verify(stockService).findBySku(ITEM_UNO.sku)
        verify(stockService).findBySku(ITEM_TRES.sku)
        verify(invoiceSale).invoke(EXPECTED_SALE_REQUEST_PARTIALLY_PROCESSED)
        verify(rejectedSaleService).saveRejectedSale(REJECTED_SALE_UNO, listOf(REJECTED_ITEM_DETAIL_UNO))
    }

    private fun thenTheOnlineSaleIsTotallyRejected() {
        verify(clientService).findByDocument(CLIENT.documentNumber)
        verify(itemService).findItemById(ITEM_CUATRO.id)
        verify(itemService).findItemById(ITEM_TRES.id)
        verify(stockService).findBySku(ITEM_CUATRO.sku)
        verify(stockService).findBySku(ITEM_TRES.sku)
        verify(rejectedSaleService).saveRejectedSale(REJECTED_SALE_DOS, listOf(REJECTED_ITEM_DETAIL_DOS, REJECTED_ITEM_DETAIL_UNO))
    }

    private companion object {
        val CLIENT = Client(0L, "00000000","Name", "Sar", "", 0.0, "", "", "Domicilio falso 123")

        val ITEM_UNO = Item(1L, "1", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val ITEM_DOS = Item(2L, "2", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val ITEM_TRES = Item(3L, "3", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val ITEM_CUATRO = Item(4L, "4", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)



        val STOCK_UNO = Stock(1L, "1", 1L, 5, 0,0, "")
        val STOCK_DOS = Stock(2L, "2", 1L, 5, 0,0, "")
        val STOCK_TRES = Stock(3L, "3", 1L, 0, 0,0, "")
        val STOCK_CUATRO = Stock(4L, "4", 1L, 0, 0,0, "")

        val SALE_ITEM_DETAIL_UNO = SaleDetail(1L, "1","",5, 752.50)
        val SALE_ITEM_DETAIL_DOS = SaleDetail(2L, "2","",5, 287.50)
        val SALE_ITEM_DETAIL_TO_REJECT_UNO = SaleDetail(3L, "3","",5, 287.50)

        val SALE_PAYMENT_DETAIL = listOf(SalePayment(0L,0L,5L,0L,"","",1040.0))

        val INVOICE_UNO = Invoice(1L, 1L, "", Date(), "A", CLIENT, 1L,"Domicilio fiscal", "1134567892", "27-28033514-8", "01/01/2021",
            SaleDetails(listOf(SALE_ITEM_DETAIL_UNO, SALE_ITEM_DETAIL_DOS), SALE_PAYMENT_DETAIL), 1040.0, 0.0, 1040.0)

        val INVOICE_DOS = Invoice(2L, 2L, "", Date(), "A", CLIENT, 1L,"Domicilio fiscal", "1134567892", "27-28033514-8", "01/01/2021",
            SaleDetails(listOf(SALE_ITEM_DETAIL_UNO, SALE_ITEM_DETAIL_TO_REJECT_UNO), SALE_PAYMENT_DETAIL), 752.50, 0.0, 752.50)

        val REJECTED_ITEM_DETAIL_UNO = RejectedItemDetail(0L, 3L, "3", "2", 5, 287.50, "No stock available")
        val REJECTED_ITEM_DETAIL_DOS = RejectedItemDetail(0L, 4L, "4", "", 5, 150.50, "No stock available")


        val REJECTED_SALE_UNO = RejectedSale(0L, 2L, 0.0, "VENTA_ONLINE", "invalid items or stocks", "PARCIAL")
        val REJECTED_SALE_DOS = RejectedSale(0L, null, 0.0, "VENTA_ONLINE", "invalid items or stocks", "TOTAL")

        val SALE_REQUEST_TO_PROCESS = SaleRequest(
            invoiceType = "B",
            clientRequest = ClientRequest(
                id = 14L,
                documentNumber = "00000000",
                firstName = "",
                lastName = "",
                state = "",
                creditLimit = 0.0,
                email = "",
                contactNumber = "",
                address = "Domicilio falso 123"
            ),
            salesmanId = 1L,
            branchId = 1L,
            saleDetailsRequest = SaleDetailsRequest(
                detailsRequest = listOf(SaleDetailRequest(1L, "", 5, 150.50),
                                        SaleDetailRequest(2L, "", 5, 57.50)),
                paymentsRequest = listOf(
                    PaymentRequest(
                        PaymentMethodRequest(0L,"MERCADO PAGO","Mercado Pago"),
                        sub_total = 150.50,null,null,null)
                )
            ),
            category = ""
        )

        val SALE_REQUEST_TO_PROCESS_PARTIALLY = SaleRequest(
            invoiceType = "B",
            clientRequest = ClientRequest(
                id = 14L,
                documentNumber = "00000000",
                firstName = "",
                lastName = "",
                state = "",
                creditLimit = 0.0,
                email = "",
                contactNumber = "",
                address = "Domicilio falso 123"
            ),
            salesmanId = 1L,
            branchId = 1L,
            saleDetailsRequest = SaleDetailsRequest(
                detailsRequest = listOf(SaleDetailRequest(1L, "", 5, 150.50),
                                        SaleDetailRequest(3L, "2",5, 287.50)),
                paymentsRequest = listOf(
                    PaymentRequest(PaymentMethodRequest(0L,"MERCADO PAGO","Mercado Pago"),
                        sub_total = 150.50,null,null,null)
                )
            ),
            category = ""
        )

        val EXPECTED_SALE_REQUEST_PARTIALLY_PROCESSED = SaleRequest(
            invoiceType = "B",
            clientRequest = ClientRequest(
                id = 14L,
                documentNumber = "00000000",
                firstName = "",
                lastName = "",
                state = "",
                creditLimit = 0.0,
                email = "",
                contactNumber = "",
                address = "Domicilio falso 123"
            ),
            salesmanId = 1L,
            branchId = 1L,
            saleDetailsRequest = SaleDetailsRequest(
                detailsRequest = listOf(SaleDetailRequest(1L, "", 5, 150.50)),
                paymentsRequest = listOf(
                    PaymentRequest(PaymentMethodRequest(0L,"MERCADO PAGO","Mercado Pago"),
                        sub_total = 150.50,null,null,null)
                )
            ),
            category = ""
        )

        val SALE_REQUEST_TO_REJECT_TOTALLY = SaleRequest(
            invoiceType = "B",
            clientRequest = ClientRequest(
                id = 14L,
                documentNumber = "00000000",
                firstName = "",
                lastName = "",
                state = "",
                creditLimit = 0.0,
                email = "",
                contactNumber = "",
                address = "Domicilio falso 123"
            ),
            salesmanId = 1L,
            branchId = 1L,
            saleDetailsRequest = SaleDetailsRequest(
                detailsRequest = listOf(SaleDetailRequest(4L, "", 5, 150.50),
                    SaleDetailRequest(3L, "2",5, 287.50)),
                paymentsRequest = listOf(
                    PaymentRequest(PaymentMethodRequest(0L,"MERCADO PAGO","Mercado Pago"),
                        sub_total = 150.50,null,null,null)
                )
            ),
            category = ""
        )
    }
}