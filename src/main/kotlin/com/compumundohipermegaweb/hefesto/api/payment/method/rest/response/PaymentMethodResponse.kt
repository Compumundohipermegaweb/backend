package com.compumundohipermegaweb.hefesto.api.payment.method.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaymentMethodResponse(@JsonProperty("id") val id: Long,
                                 @JsonProperty("type") val type: String,
                                 @JsonProperty("description") val description: String,
                                 @JsonProperty("state") val state: String)

