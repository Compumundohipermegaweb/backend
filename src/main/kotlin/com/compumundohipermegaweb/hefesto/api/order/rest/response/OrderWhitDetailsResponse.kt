package com.compumundohipermegaweb.hefesto.api.order.rest.response

import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleDetailsResponse
import com.compumundohipermegaweb.hefesto.api.sale.rest.response.SaleItemDetailResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrderWhitDetailsResponse(@JsonProperty("id") val id: Long,
                                    @JsonProperty("sale_id") val saleId: Long,
                                    @JsonProperty("state") val state: String,
                                    @JsonProperty("shipping_price") val shippingPrice: Double,
                                    @JsonProperty("shipping_company") val shippingCompany: String,
                                    @JsonProperty("items_detail") val itemsDetail: SaleDetailsResponse
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class OrdersWhitDetailsResponse(@JsonProperty("orders") val orderWhitDetails: List<OrderWhitDetailsResponse>)