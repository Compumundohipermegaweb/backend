package com.compumundohipermegaweb.hefesto.api.invoice.domain.model

interface InvoiceRepository {
    fun save(invoice: Invoice): Invoice
}