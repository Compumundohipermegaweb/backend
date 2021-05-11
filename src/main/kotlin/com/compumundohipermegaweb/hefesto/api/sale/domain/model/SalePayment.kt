package com.compumundohipermegaweb.hefesto.api.sale.domain.model

data class SalePayment(val id: Long,
                       val type: String,
                       val subTotal: Double)