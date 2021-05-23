package com.compumundohipermegaweb.hefesto.api.client.rest.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ClientBalanceResponse (
    @JsonProperty("client_id") val clientId: Long,
    @JsonProperty("balance") val balance: Double
)