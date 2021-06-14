package com.compumundohipermegaweb.hefesto.api.invoice.domain.model

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model.RejectedItemDetail
import java.util.*

data class RejectedInvoice(val rejectionDate: Date,
                           val client: Client,
                           val rejectedItems: List<RejectedItemDetail>,
                           val subTotal: Double,
                           val ivaSubTotal: Double,
                           val total: Double)
