package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientRequest
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
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
                  private val stockService: StockService
) {
    operator fun invoke(saleRequest: SaleRequest): Invoice {
        val sale = saleRequest.toSale()

        sale.saleDetails.details.forEach { stockService.reduceStock(it.id, sale.branchId, it.quantity) }

        val invoice = invoiceService.invoiceSale(sale)
        saleService.save(sale, invoice.id)

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
            total = total)


    }

    private fun SaleDetailsRequest.toSaleDetails() =
        SaleDetails(detailsRequest.map { SaleDetail(it.id, it.description, it.quantity, it.unitPrice) }, paymentsRequest.map { SalePayment(0L, it.type, it.subTotal) })

    private fun ClientRequest.toClient(): Client {
        return Client(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }
}




