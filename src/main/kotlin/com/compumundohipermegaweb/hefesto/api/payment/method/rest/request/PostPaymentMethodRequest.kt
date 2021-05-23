package com.compumundohipermegaweb.hefesto.api.payment.method.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PostPaymentMethodRequest (
        @JsonProperty("payment_method") val paymentMethod: String,
        @JsonProperty("state") val state: String
        )
