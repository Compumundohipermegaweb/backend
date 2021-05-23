package com.compumundohipermegaweb.hefesto.api.payment.method.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaymentMethodsResponse(@JsonProperty("payment_methods") val paymentMethods: List<PaymentMethodResponse>)
