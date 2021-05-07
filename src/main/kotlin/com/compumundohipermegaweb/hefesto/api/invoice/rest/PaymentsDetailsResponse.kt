package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaymentsDetailsResponse(@JsonProperty("id") val id: Long,
                                   @JsonProperty("sub_total") val subTotal: Double)
