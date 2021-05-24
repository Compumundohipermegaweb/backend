package com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model

data class RejectedSale(val id: Long,
                        val saleId: Long?,
                        var total: Double,
                        val category: String,
                        val reason: String,
                        val level: String)
