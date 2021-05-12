package com.compumundohipermegaweb.hefesto.api.stock.domain.model

data class Stock(val id: Long,
                 val sku: Long,
                 val stockTotal: Int,
                 val minimumStock: Int,
                 val securityStock: Int,
                 val supplier: String,
                 val currency: String,
                 val cost: Double)
