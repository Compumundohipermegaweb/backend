package com.compumundohipermegaweb.hefesto.api.cash.domain.model

data class Cash(val id: Long,
                val branchId: Long,
                val pointOfSale: Long,
                var status: String)
