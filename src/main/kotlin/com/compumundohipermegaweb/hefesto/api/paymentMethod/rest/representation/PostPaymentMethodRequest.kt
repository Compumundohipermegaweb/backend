package com.compumundohipermegaweb.hefesto.api.paymentMethod.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PostPaymentMethodRequest (
        @JsonProperty("payment_method") val paymentMethod: String,
        @JsonProperty("state") val state: String
        )
