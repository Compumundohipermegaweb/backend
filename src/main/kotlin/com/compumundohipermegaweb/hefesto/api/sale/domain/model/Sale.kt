package com.compumundohipermegaweb.hefesto.api.sale.domain.model

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client

data class Sale(val id: Long,
                val type: String,
                val client: Client,
                val salesmanId: Long,
                val branchId: Long,
                var saleDetails: SaleDetails,
                var total: Double,
                val category: String)
