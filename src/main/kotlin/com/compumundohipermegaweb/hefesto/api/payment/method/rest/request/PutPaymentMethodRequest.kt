package com.compumundohipermegaweb.hefesto.api.payment.method.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PutPaymentMethodRequest (
        @JsonProperty("id") val id: Long,
        @JsonProperty("description") val description: String,
        @JsonProperty("state") val state: String,
        @JsonProperty("type") val type: String)