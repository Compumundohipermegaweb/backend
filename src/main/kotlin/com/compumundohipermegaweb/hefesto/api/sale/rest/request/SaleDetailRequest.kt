package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleDetailRequest(@JsonProperty("id") val id: Long,
                             @JsonProperty("description") val description: String,
                             @JsonProperty("quantity") var quantity: Int,
                             @JsonProperty("unit_price") val unitPrice: Double)
