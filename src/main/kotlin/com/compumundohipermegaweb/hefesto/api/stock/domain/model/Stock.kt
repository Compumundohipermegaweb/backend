package com.compumundohipermegaweb.hefesto.api.stock.domain.model

data class Stock(val id: Long,
                 val sku: String,
                 val branchId: Long,
                 var stockTotal: Int,
                 val minimumStock: Int,
                 val securityStock: Int)
