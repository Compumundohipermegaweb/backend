package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaymentRequest(@JsonProperty("method") val method: PaymentMethodRequest,
                          @JsonProperty("sub_total") val sub_total: Double,
                          @JsonProperty("card_id") val cardId: Long?,
                          @JsonProperty("email") val email: String?,
                          @JsonProperty("last_digits") val lastDigits: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaymentMethodRequest (
    @JsonProperty("id") val id: Long,
    @JsonProperty("type") val type: String,
    @JsonProperty("description") val description: String?)