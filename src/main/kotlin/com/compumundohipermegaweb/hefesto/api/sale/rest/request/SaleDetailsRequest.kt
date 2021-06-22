package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleDetailsRequest(@JsonProperty("details") var detailsRequest: List<SaleDetailRequest>,
                              @JsonProperty("payments") val paymentsRequest: List<PaymentRequest>,
                              @JsonProperty("discount") val discount: DiscountRequest?)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleItemsDetailsRequest(@JsonProperty("details") var detailsRequest: List<SaleDetailRequest>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DiscountRequest(@JsonProperty("percentage") val percentage: Int,
                           @JsonProperty("amount") val amount: Double)