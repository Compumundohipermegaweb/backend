package com.compumundohipermegaweb.hefesto.api.online.sale.domain.model

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.Invoice
import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.RejectedInvoice

data class ProcessedOnlineSale(val invoice: Invoice?,
                               val rejectedInvoice: RejectedInvoice?)