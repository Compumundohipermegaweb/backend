package com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.model

data class RejectedItemDetail(var id: Long,
                              var itemId: Long?,
                              var sku: String?,
                              val description: String,
                              val quantity: Int,
                              val unitPrice: Double,
                              val reason: String)
