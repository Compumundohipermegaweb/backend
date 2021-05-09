package com.compumundohipermegaweb.hefesto.api.invoice.domain.service

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale

interface InvoiceService {
    fun invoiceSale(sale: Sale): Invoice
}