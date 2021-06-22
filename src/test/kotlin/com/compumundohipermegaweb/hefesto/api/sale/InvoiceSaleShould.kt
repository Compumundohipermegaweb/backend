package com.compumundohipermegaweb.hefesto.api.sale

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.discount.domain.model.Discount
import com.compumundohipermegaweb.hefesto.api.discount.domain.repositorty.DiscountRepository
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.item.domain.model.Item
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService
import com.compumundohipermegaweb.hefesto.api.sale.domain.action.InvoiceSale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.*
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import java.util.*

class InvoiceSaleShould {

    private lateinit var saleService: SaleService
    private lateinit var invoiceService: InvoiceService
    private lateinit var stockService: StockService
    private lateinit var itemService: ItemService
    private lateinit var checkingAccountService: CheckingAccountService
    private lateinit var paymentMethodService: PaymentMethodService
    private lateinit var discountRepository: DiscountRepository

    private lateinit var generatedInvoice: Invoice

    private lateinit var invoiceSale: InvoiceSale

    @Test
    fun `generate the invoice of the sale`() {
        givenSaleService()
        givenInvoiceService()
        givenStockService()
        givenItemService()
        givenCheckingAccountService()
        givenPaymentMethodService()
        givenDiscountRepository()
        givenInvoiceSale()

        whenInvoiceSale(TYPE_A_SALE_REQUEST)

        thenTheInvoiceIsGenerated()
    }

    @Test
    fun `save the sale`() {
        givenSaleService()
        givenInvoiceService()
        givenStockService()
        givenItemService()
        givenCheckingAccountService()
        givenPaymentMethodService()
        givenDiscountRepository()
        givenInvoiceSale()

        whenInvoiceSale(TYPE_A_SALE_REQUEST)

        verify(saleService, times(2)).save(any(), any())
    }

    @Test
    fun `should discount client credit if uses it checking account`() {
        givenSaleService()
        givenInvoiceService()
        givenStockService()
        givenItemService()
        givenCheckingAccountService()
        givenPaymentMethodService()
        givenDiscountRepository()
        givenInvoiceSale()

        whenInvoiceSale(SALE_WITH_CHECKING_ACCOUNT_PAYMENT)
    }

    @Test
    fun `save the discount`() {
        givenSaleService()
        givenInvoiceService()
        givenStockService()
        givenItemService()
        givenCheckingAccountService()
        givenPaymentMethodService()
        givenDiscountRepository()
        givenInvoiceSale()

        whenInvoiceSale(SALE_WITH_DISCOUNT)

        thenDiscountIsSaved()
    }

    private fun givenDiscountRepository() {
        discountRepository = mock()
    }

    private fun givenSaleService() {
        saleService = mock()
        `when`(saleService.save(any(), any())).thenReturn(SAVED_SALE_TYPE_A)
    }

    private fun givenInvoiceService() {
        invoiceService = mock()
        `when`(invoiceService.invoiceSale(any(), any())).thenReturn(SAVED_INVOICE_TYPE_A)
        `when`(invoiceService.updateInvoice(SAVED_INVOICE_TYPE_A)).thenReturn(SAVED_INVOICE_TYPE_A)
    }

    private fun givenStockService() {
        stockService = mock()
        `when`(stockService.reduceStock(any(), any(), any())).then {  }
    }

    private fun givenItemService() {
        itemService = mock()
        `when`(itemService.findItemById(0L)).thenReturn(ITEM)
    }

    private fun givenCheckingAccountService() {
        checkingAccountService = mock()

    }

    private fun givenPaymentMethodService() {
        paymentMethodService = mock()
    }

    private fun givenInvoiceSale() {
        invoiceSale = InvoiceSale(saleService, invoiceService, stockService, itemService, checkingAccountService, paymentMethodService, discountRepository)
    }

    private fun whenInvoiceSale(saleRequest: SaleRequest) {
        generatedInvoice = invoiceSale.invoke(saleRequest)
    }

    private fun thenTheInvoiceIsGenerated() {
        then(generatedInvoice).isNotNull
    }

    private fun thenDiscountIsSaved() {
        verify(discountRepository).save(Discount(0L, 10, 100.0, 0L))
    }

    private companion object {
        val DEFAULT_CLIENT = Client(0L, "99999999", "Consumidor", "Final", "", 0.0, "", "", "")
        val CLIENT_REQUEST = ClientRequest(0L,"", "", "", "", 0.0, "", "", "")
        val CLIENT = Client(0L, "", "", "", "", 0.0, "", "", "")
        val ITEM = Item(0L, "0", "", "", 1L, 1L, "", 1.0, 10.0, false, "", 0)
        val SALE_ITEM_DETAIL_REQUEST = listOf(SaleDetailRequest(0L, "",1, 200.50))
        val PAYMENT_METHOD_REQUEST = PaymentMethodRequest(5L,"CUENTA_CORRIENTE","CUENTA CORRIENTE")
        val SALE_PAYMENT_DETAIL_REQUEST = listOf(PaymentRequest(PAYMENT_METHOD_REQUEST,200.50,null,null,null))
        val SALE_DETAILS_REQUEST = SaleDetailsRequest(SALE_ITEM_DETAIL_REQUEST, SALE_PAYMENT_DETAIL_REQUEST, null)
        val TYPE_A_SALE_REQUEST = SaleRequest("A", CLIENT_REQUEST, 0L, 0L, SALE_DETAILS_REQUEST, "")
        val SALE_ITEM_DETAIL = listOf(SaleDetail(0L, "","",1, 200.50))
        val SALE_PAYMENT_DETAIL = listOf(SalePayment(0L,0L,0L,0L,"","",200.50))
        val SALE_DETAILS = SaleDetails(SALE_ITEM_DETAIL, SALE_PAYMENT_DETAIL)
        val SAVED_SALE_TYPE_A = Sale(0L, TYPE_A_SALE_REQUEST.invoiceType, CLIENT, TYPE_A_SALE_REQUEST.salesmanId, TYPE_A_SALE_REQUEST.branchId, SALE_DETAILS, 200.5, "")
        val SAVED_INVOICE_TYPE_A = Invoice(0L, 0L, "", Date(), SAVED_SALE_TYPE_A.type, DEFAULT_CLIENT, SAVED_SALE_TYPE_A.branchId,"Domicilio fiscal", "1134567892", "27-28033514-8", "01/01/2021", SALE_DETAILS, 200.50, 42.105, 200.50)
        val SALE_WITH_CHECKING_ACCOUNT_PAYMENT = SaleRequest(
                invoiceType = "B",
                clientRequest = ClientRequest(
                        id = 14L,
                        documentNumber = "",
                        firstName = "",
                        lastName = "",
                        state = "",
                        creditLimit = 0.0,
                        email = "",
                        contactNumber = "",
                        address = ""
                ),
                salesmanId = 1L,
                branchId = 1L,
                saleDetailsRequest = SaleDetailsRequest(
                        detailsRequest = listOf(
                                SaleDetailRequest(
                                        id = 1L,
                                        description = "",
                                        quantity = 1,
                                        unitPrice = 150.50
                                )
                        ),
                        paymentsRequest = listOf(
                            PaymentRequest(PaymentMethodRequest(5L,"CUENTA_CORRIENTE","CUENTA CORRIENTE"),
                                sub_total = 150.50,null,null,null)
                        ),
                discount = null),
                category = ""
        )

        val SALE_DETAILS_WITH_DISCOUNT = SaleDetailsRequest(SALE_ITEM_DETAIL_REQUEST, SALE_PAYMENT_DETAIL_REQUEST, DiscountRequest(10, 100.0))
        val SALE_WITH_DISCOUNT = SaleRequest(
                invoiceType = "B",
                clientRequest = CLIENT_REQUEST,
                salesmanId = 1L,
                branchId = 1L,
                saleDetailsRequest = SALE_DETAILS_WITH_DISCOUNT,
                category = null
        )
    }
}