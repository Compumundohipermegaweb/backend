package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.ItemDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.PaymentDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailsRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest

class InvoiceSale(private val saleService: SaleService,
                  private val invoiceService: InvoiceService
) {
    operator fun invoke(saleRequest: SaleRequest): Invoice {

        val savedSale = saleService.save(saleRequest.toSale())

        return invoiceService.invoiceSale(savedSale)
    }

    private fun SaleRequest.toSale() =
            Sale(id = 0L,
                type = type,
                clientId = 0L,
                salesmanId = idSalesman,
                branchId = idBranch,
                saleDetails = saleDetailsRequest.toSaleDetails(),
                total = total)

    private fun SaleDetailsRequest.toSaleDetails() =
            SaleDetails(itemDetailsRequest.map { ItemDetail(it.id, it.quantity, it.unitPrice) }, paymentDetailsRequest.map { PaymentDetail(0L, it.type, it.subTotal) })

}


