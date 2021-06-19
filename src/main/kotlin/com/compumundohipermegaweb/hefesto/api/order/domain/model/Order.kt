package com.compumundohipermegaweb.hefesto.api.order.domain.model

data class Order(val id: Long,
                 val saleId: Long,
                 val branchId: Long,
                 var state: String,
                 var shippingPrice: Double,
                 var shippingCompany: String)
