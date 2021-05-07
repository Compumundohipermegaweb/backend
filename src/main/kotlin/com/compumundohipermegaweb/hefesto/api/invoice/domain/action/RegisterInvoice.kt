package com.compumundohipermegaweb.hefesto.api.invoice.domain.action

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.InvoiceRepository

class RegisterInvoice(private val invoiceRepository: InvoiceRepository){
    operator fun invoke(invoice: Invoice): Invoice = invoiceRepository.save(invoice)
}