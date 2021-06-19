package com.compumundohipermegaweb.hefesto.api.order.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrderResponse(@JsonProperty("id") val id: Long,
                         @JsonProperty("sale_id") val saleId: Long,
                         @JsonProperty("state") val state: String,
                         @JsonProperty("shipping_price") val shippingPrice: Double,
                         @JsonProperty("shipping_company") val shippingCompany: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrdersResponse(@JsonProperty("orders") val orders: List<OrderResponse>)