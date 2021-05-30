package com.compumundohipermegaweb.hefesto.api.cash.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OpenRequest(@JsonProperty("CASH_ID") val cashId: Long,
                       @JsonProperty("USER_ID") val userId: Long,
                       @JsonProperty("OPENING_BALANCE") val openingBalance: Double)
