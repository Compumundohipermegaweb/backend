package com.compumundohipermegaweb.hefesto.api.item.domain.model

data class Item(val id: Long,
                val sku: String,
                val shortDescription: String,
                val description: String,
                val brandId: Long,
                val categoryId: Long,
                val uomSale: String,
                val price: Double,
                val imported: Boolean,
                val state: String,
                var availableStock: Int)
