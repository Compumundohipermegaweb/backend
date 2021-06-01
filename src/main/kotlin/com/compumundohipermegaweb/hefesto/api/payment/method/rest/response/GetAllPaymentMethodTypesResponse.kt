package com.compumundohipermegaweb.hefesto.api.payment.method.rest.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GetAllPaymentMethodTypesResponse(
        @JsonProperty("types") val types: List<String>)