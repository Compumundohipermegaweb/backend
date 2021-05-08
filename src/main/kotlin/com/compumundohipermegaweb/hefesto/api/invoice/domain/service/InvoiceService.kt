package com.compumundohipermegaweb.hefesto.api.invoice.domain.service

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice

interface InvoiceService {
    fun save(invoice: Invoice): Invoice
}