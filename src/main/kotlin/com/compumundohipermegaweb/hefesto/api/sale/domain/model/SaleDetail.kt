package com.compumundohipermegaweb.hefesto.api.sale.domain.model

data class SaleDetail(
    var id: Long,
    var sku: String,
    val description: String,
    val quantity: Int,
    val unitPrice: Double)
