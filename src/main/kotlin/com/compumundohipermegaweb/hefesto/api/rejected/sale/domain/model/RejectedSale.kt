package com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model

data class RejectedSale(val id: Long,
                        val saleId: Long,
                        val total: Double,
                        val category: String,
                        val reason: String)
