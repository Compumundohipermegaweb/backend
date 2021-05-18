package com.compumundohipermegaweb.hefesto.api.invoice.infra.repository

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.representation.InvoiceDao

class JpaInvoiceRepository(private val springDataInvoiceClient: SpringDataInvoiceClient): InvoiceRepository  {
    override fun save(invoice: Invoice): Invoice {
        val savedInvoice = springDataInvoiceClient.save(invoice.toInvoiceDao())
        savedInvoice.voucherNumber = "0000000" + savedInvoice.id
        return springDataInvoiceClient.save(savedInvoice).toInvoice(invoice)
    }

    private fun InvoiceDao.toInvoice(invoice: Invoice): Invoice {
        return Invoice(id, documentType+"0000"+branchId+voucherNumber, billingDate, type, invoice.client, branchId, invoice.branchAddress, invoice.branchContact, invoice.cuit, invoice.activitySince, invoice.saleDetails, subTotal, ivaSubTotal, total)
    }

    private fun Invoice.toInvoiceDao(): InvoiceDao {
        return InvoiceDao(id, voucherNumber, billingDate, "FV$type", type, branchId, subTotal, ivaSubTotal, total)
    }
}