package com.compumundohipermegaweb.hefesto.api.invoice.infra.repository

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.representation.InvoiceDao

class JpaInvoiceRepository(private val springDataInvoiceRepository: SpringDataInvoiceRepository): InvoiceRepository  {
    override fun save(invoice: Invoice): Invoice {
        return springDataInvoiceRepository.save(invoice.toInvoiceDao()).toInvoice(invoice)
    }

    private fun InvoiceDao.toInvoice(invoice: Invoice): Invoice {
        return Invoice(id, billingDate, saleId, type, invoice.client, branchId, invoice.branchAddress, invoice.branchContact, invoice.cuit, invoice.activitySince, invoice.saleDetails, subTotal, ivaSubTotal, total)
    }

    private fun Invoice.toInvoiceDao(): InvoiceDao {
        return InvoiceDao(id, billingDate, saleId, type, branchId, subTotal, ivaSubTotal, total)
    }
}