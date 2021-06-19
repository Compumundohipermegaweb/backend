package com.compumundohipermegaweb.hefesto.api.order.rest.request

import com.compumundohipermegaweb.hefesto.api.sale.rest.request.SaleItemsDetailsRequest
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrderWhitDetailsRequest(@JsonProperty("id") val id: Long,
                                   @JsonProperty("sale_id") val saleId: Long,
                                   @JsonProperty("branch_id") val branchId: Long,
                                   @JsonProperty("user_id") val userId: Long,
                                   @JsonProperty("cash_id") val cashId: Long,
                                   @JsonProperty("state") val state: String,
                                   @JsonProperty("shipping_price") val shippingPrice: Double,
                                   @JsonProperty("shipping_company") val shippingCompany: String,
                                   @JsonProperty("items_detail") val itemsDetail: SaleItemsDetailsRequest
)