package com.compumundohipermegaweb.hefesto.api.cash.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CloseRequest(@JsonProperty("cash_id") val cashId: Long,
                        @JsonProperty("user_id") val userId: Long,
                        @JsonProperty("real_balance") val realBalance: Double,
                        @JsonProperty("theoretical_balance") val theoreticalBalance: Double)