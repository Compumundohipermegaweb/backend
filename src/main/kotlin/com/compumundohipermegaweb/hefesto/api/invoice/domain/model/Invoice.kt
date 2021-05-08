package com.compumundohipermegaweb.hefesto.api.invoice.domain.model

data class Invoice(val id: Long,
                   val saleId: Long,
                   val type: String,
                   val branchId: Long,
                   val subTotal: Double,
                   val ivaSubTotal: Double,
                   val total: Double)
