package com.compumundohipermegaweb.hefesto.api.order.domain.model

data class Order(val id: Long,
                 val saleId: Long,
                 val branchId: Long,
                 var state: String,
                 val shippingPrice: Double,
                 val shippingCompany: String)
