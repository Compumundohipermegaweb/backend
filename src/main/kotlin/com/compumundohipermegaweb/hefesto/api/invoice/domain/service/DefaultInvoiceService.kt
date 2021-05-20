package com.compumundohipermegaweb.hefesto.api.invoice.domain.service

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import java.util.*

class DefaultInvoiceService(private val invoiceRepository: InvoiceRepository): InvoiceService {

    override fun invoiceSale(sale: Sale) : Invoice {
        val iva = calculateIva(sale)
        val subTotal = calculateSubTotal(sale, iva)
        val invoice = createInvoice(sale, subTotal, iva)

        return invoiceRepository.save(invoice)
    }


    private fun calculateIva(savedSale: Sale): Double {
        return when(savedSale.type){
            "A" -> (21 * savedSale.total) / 100
            else -> 0.0
        }
    }

    private fun calculateSubTotal(savedSale: Sale, iva: Double): Double {
        return when(savedSale.type){
            "A" -> savedSale.total - iva
            else -> savedSale.total
        }
    }

    private fun createInvoice(sale: Sale, subTotal: Double, iva: Double) =
            Invoice(id =0L,
                    voucherNumber = "",
                    billingDate = Date(),
                    type = sale.type,
                    client = sale.client,//defaultClient(sale.type),
                    branchId = sale.branchId,
                    branchAddress = "Domicilio fiscal",
                    branchContact = "1134567892",
                    cuit = "27-28033514-8",
                    activitySince = "01/01/2021",
                    saleDetails = sale.saleDetails,
                    subTotal = subTotal,
                    ivaSubTotal = iva,
                    total = sale.total)
/*
    private fun defaultClient(invoiceType: String): Client {
        return when(invoiceType) {
            "B" -> Client(0L, "99999999", "Consumidor", "Final", "", 0.0, "", "")
            else -> Client(0L, "", "", "", "", 0.0, "", "")
        }
    }
*/
}

