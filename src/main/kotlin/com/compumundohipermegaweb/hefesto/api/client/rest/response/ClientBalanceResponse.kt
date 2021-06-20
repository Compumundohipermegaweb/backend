package com.compumundohipermegaweb.hefesto.api.client.rest.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientBalanceResponse (
    @JsonProperty("id") val id: Long,
    @JsonProperty("balance") val balance: Double,
    @JsonProperty("balance_due") val balanceDue: Double,
    @JsonProperty("credit_limit") val creditLimit: Double
)