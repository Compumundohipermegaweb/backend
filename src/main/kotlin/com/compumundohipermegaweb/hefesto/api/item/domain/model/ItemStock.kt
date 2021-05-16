package com.compumundohipermegaweb.hefesto.api.item.domain.model

data class ItemStock(
        val id: Long,
        val sku: String,
        val shortDescription: String,
        val longDescription: String,
        val brandName: String,
        val price: Double,
        val availableStock: Int,
        val imported: Boolean)
