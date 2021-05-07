package com.compumundohipermegaweb.hefesto.api.invoice.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ShortPaymentsDetails(@JsonProperty("id") val id: Long,
                                @JsonProperty("sub_total") val subTotal: Double)
