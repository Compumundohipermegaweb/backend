package com.compumundohipermegaweb.hefesto.api.invoice.domain.model

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client

data class Invoice(
    val id: Long,
    val type: String,
    val client: Client,
    val idSalesman: Long,
    val idBranch: Long,
    val items: ShortItems,
    val total: Double,
    val shortPaymentsDetails: ShortPayments
)