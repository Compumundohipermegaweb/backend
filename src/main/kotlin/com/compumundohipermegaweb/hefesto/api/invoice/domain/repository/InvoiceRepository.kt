package com.compumundohipermegaweb.hefesto.api.invoice.domain.repository

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice

interface InvoiceRepository {
    fun save(invoice: Invoice): Invoice
}