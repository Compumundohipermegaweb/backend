package com.compumundohipermegaweb.hefesto.api.sale.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleDetailResponse(@JsonProperty("id") val id: Long,
                              @JsonProperty("quantity") val quantity: Int,
                              @JsonProperty("unit_price") val unitPrice: Double)
