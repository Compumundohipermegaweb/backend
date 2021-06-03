package com.compumundohipermegaweb.hefesto.api.invoice.domain.service

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.Sale
import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleRequest

interface InvoiceService {
    fun invoiceSale(sale: Sale, saleRequest: SaleRequest): Invoice
    fun updateInvoice(invoice: Invoice): Invoice
}