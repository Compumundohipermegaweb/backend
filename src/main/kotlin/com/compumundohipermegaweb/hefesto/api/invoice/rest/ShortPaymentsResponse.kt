package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShortPaymentsResponse(@JsonProperty("payments") val payments: List<PaymentsDetailsResponse>)
