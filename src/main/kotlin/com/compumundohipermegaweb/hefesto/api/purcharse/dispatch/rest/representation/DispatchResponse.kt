package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class DispatchResponse(
        @JsonProperty("status") val status: String,
        @JsonProperty("errors") val errors: List<DispatchErrorResponse>)

data class DispatchErrorResponse(
        @JsonProperty("code") val code: String,
        @JsonProperty("dispatched_item") val dispatchedItemResponse: DispatchedItemResponse)

data class DispatchedItemResponse(
        @JsonProperty("sku") val sku: String,
        @JsonProperty("amount") val amount: Int,
        @JsonProperty("unit_price") val unitPrice: Double,
        @JsonProperty("subtotal") val subtotal: Double)
