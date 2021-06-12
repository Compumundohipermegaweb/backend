package com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model

data class PurchaseOrder(
        val id: Long,
        val sku: String,
        val amount: Int,
        val supplier: String)
