package com.compumundohipermegaweb.hefesto.api.checking.account.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CheckingAccountRequest (
    @JsonProperty("client_id") val clientId: Long,
    @JsonProperty("credit_limit") val creditLimit: Double
)