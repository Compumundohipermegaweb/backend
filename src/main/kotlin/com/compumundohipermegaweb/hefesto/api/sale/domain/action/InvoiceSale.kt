package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.DefaultInvoiceService
import com.compumundohipermegaweb.hefesto.api.invoice.domain.service.InvoiceService
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetails
import com.compumundohipermegaweb.hefesto.api.sale.domain.service.SaleService
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleDetailsRequest
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest

class InvoiceSale(private val saleService: SaleService,
                  private val invoiceService: InvoiceService
) {
    operator fun invoke(saleRequest: SaleRequest): Invoice {

        val savedSale = saleService.save(saleRequest.toSale())
        val iva = calculateIva(savedSale)
        val subTotal = calculateSubTotal(savedSale, iva)
        val invoice = createInvoice(savedSale, subTotal, iva)

        return invoiceService.save(invoice)
    }

    private fun calculateSubTotal(savedSale: Sale, iva: Double) = savedSale.total - iva

    private fun calculateIva(savedSale: Sale) = (21 * savedSale.total) / 100

    private fun createInvoice(sale: Sale, subTotal: Double, iva: Double) = Invoice(0L, sale.id, sale.type, sale.branchId, subTotal, iva, sale.total)

    private fun SaleRequest.toSale() = Sale(0L, type,0L, idSalesman, idBranch, saleDetailsRequest.toSaleDetails(), total)

    private fun SaleDetailsRequest.toSaleDetails() = SaleDetails(saleDetailsRequest.map { SaleDetail(it.id, it.quantity, it.unitPrice) })

}


