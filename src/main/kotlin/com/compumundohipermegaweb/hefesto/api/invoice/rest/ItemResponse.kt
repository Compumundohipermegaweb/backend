package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemResponse(@JsonProperty("id") val id: Long,
                        @JsonProperty("quantity") val quantity: Int,
                        @JsonProperty("unit_price") val unitPrice: Double,
                        @JsonProperty("total_price") val totalPrice: Double)