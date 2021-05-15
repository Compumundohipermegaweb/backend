package com.compumundohipermegaweb.hefesto.api.stock.domain.model

data class Stock(val id: Long,
                 val sku: Long,
                 val branchId: Long,
                 val stockTotal: Int,
                 val minimumStock: Int,
                 val securityStock: Int)
