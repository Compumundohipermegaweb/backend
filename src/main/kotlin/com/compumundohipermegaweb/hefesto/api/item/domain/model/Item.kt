package com.compumundohipermegaweb.hefesto.api.item.domain.model

data class Item(val id: Long,
                val sku: String,
                val shortDescription: String,
                val longDescription: String,
                val measure: String,
                val stockTotal: Int,
                val minimumStock: Int,
                val securityStock: Int,
                val supplier: String,
                val cost: Double,
                val unitPrice: Double)
