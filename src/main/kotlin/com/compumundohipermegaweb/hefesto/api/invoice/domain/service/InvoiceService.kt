package com.compumundohipermegaweb.hefesto.api.invoice.domain.service

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.repository.InvoiceRepository

class InvoiceService(private val invoiceRepository: InvoiceRepository) {
    fun save(invoice: Invoice) : Invoice {
        return invoiceRepository.save(invoice)
    }
}