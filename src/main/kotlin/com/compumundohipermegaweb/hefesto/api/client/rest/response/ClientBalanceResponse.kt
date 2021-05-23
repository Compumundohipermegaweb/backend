package com.compumundohipermegaweb.hefesto.api.client.rest.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientBalanceResponse (
    @JsonProperty("id") val id: Long,
    @JsonProperty("balance") val balance: Double
)