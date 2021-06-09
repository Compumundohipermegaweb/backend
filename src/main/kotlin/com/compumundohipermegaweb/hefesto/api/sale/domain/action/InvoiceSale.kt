package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.item.domain.service.ItemService
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailsRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest
import com.compumundohipermegaweb.hefesto.api.stock.domain.service.StockService

class InvoiceSale(private val saleService: SaleService,
                  private val invoiceService: InvoiceService,
                  private val stockService: StockService,
                  private val itemService: ItemService,
                  private val checkingAccountService: CheckingAccountService,
                  private val paymentMethodService: PaymentMethodService
) {
    operator fun invoke(saleRequest: SaleRequest): Invoice {
        val sale = saleRequest.toSale()

        sale.saleDetails.details.forEach {
            stockService.reduceStock(it.id, sale.branchId, it.quantity)
        }

        sale.saleDetails.payments.forEach {
            val method=paymentMethodService.findById(it.paymentMethodId)
            if( method?.type == "CUENTA_CORRIENTE") {
                checkingAccountService.discount(sale.client.id, it.subTotal)
            }
        }

        val savedSale = saleService.save(sale, 0L)
        val invoice = invoiceService.invoiceSale(savedSale, saleRequest)
        saleService.save(savedSale, invoice.id)

        return invoice
    }

    private fun SaleRequest.toSale(): Sale {
        val saleDetails = saleDetailsRequest.toSaleDetails()
        val total = saleDetails.details.map { it.quantity * it.unitPrice }.reduce { acc, d -> acc + d }
        return Sale(id = 0L,
            type = invoiceType,
            client = clientRequest.toClient(),
            salesmanId = salesmanId,
            branchId = branchId,
            saleDetails = saleDetails,
            total = total,
            category = "VENTA_ONLINE")
    }

    private fun SaleDetailsRequest.toSaleDetails(): SaleDetails {
        val saleDetails = SaleDetails(detailsRequest.map { SaleDetail(it.id, "", it.description, it.quantity, it.unitPrice) },
                                                            paymentsRequest.map { SalePayment(0L,0L,it.method.id,it.cardId,it.lastDigits,it.email,it.sub_total) })
        saleDetails.details.forEach {
            val item = itemService.findItemById(it.id)
            if(item != null) {
                it.sku = item.sku
            }
        }
        return saleDetails
    }

    private fun ClientRequest.toClient(): Client {
        return Client(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber, address)
    }
}




