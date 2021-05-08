package com.compumundohipermegaweb.hefesto.api.invoice.infra.repository

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository
import com.compumundohipermegaweb.hefesto.api.invoice.infra.representation.InvoiceDao

class JpaInvoiceRepository(private val springDataInvoiceRepository: SpringDataInvoiceRepository): InvoiceRepository  {
    override fun save(invoice: Invoice): Invoice {
        return springDataInvoiceRepository.save(invoice.toInvoiceDao()).toInvoice()
    }

    private fun InvoiceDao.toInvoice(): Invoice {
        return Invoice(id, saleId, type, branchId, subTotal, ivaSubTotal, total)
    }

    private fun Invoice.toInvoiceDao(): InvoiceDao {
        return InvoiceDao(id, saleId, type, branchId, subTotal, ivaSubTotal, total)
    }
}