package com.compumundohipermegaweb.hefesto.api.order.domain.model

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SaleDetail

data class OrderWhitItemDetails(val id: Long,
                                val saleId: Long,
                                val branchId: Long,
                                val userId: Long,
                                val cashId: Long,
                                val state: String,
                                val shippingPrice: Double,
                                val shippingCompany: String,
                                val saleItemDetails: List<SaleDetail>)
