package com.compumundohipermegaweb.hefesto.api.invoice.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ShortItem(@JsonProperty("id") val id: Long,
                     @JsonProperty("quantity") val quantity: Int,
                     @JsonProperty("unit_price") val unitPrice: Double)
