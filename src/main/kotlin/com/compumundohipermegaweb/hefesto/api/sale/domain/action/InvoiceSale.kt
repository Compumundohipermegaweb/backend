package com.compumundohipermegaweb.hefesto.api.sale.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
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

    private fun calculateSubTotal(savedSale: Sale, iva: Double): Double {
        return when(savedSale.type){
            "A" -> savedSale.total - iva
            else -> savedSale.total
        }
    }

    private fun calculateIva(savedSale: Sale): Double {
        return when(savedSale.type){
            "A" -> (21 * savedSale.total) / 100
            else -> 0.0
        }
    }

    private fun createInvoice(sale: Sale, subTotal: Double, iva: Double) = Invoice(0L, sale.id, sale.type, defaultClient(), sale.branchId, "Domicilio fiscal", "1134567892", "27-28033514-8", "01/01/2021", sale.saleDetails, subTotal, iva, sale.total)

    private fun defaultClient() = Client(0L, "99999999", "Consumidor", "Final", "", "", "", "")

    private fun SaleRequest.toSale() = Sale(0L, type,0L, idSalesman, idBranch, saleDetailsRequest.toSaleDetails(), total)

    private fun SaleDetailsRequest.toSaleDetails() = SaleDetails(saleDetailsRequest.map { SaleDetail(it.id, it.quantity, it.unitPrice) })

}


