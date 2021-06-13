package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DispatchRequest(
        @JsonProperty("dispatch_id") val dispatchId: Long,
        @JsonProperty("supplier_id") val supplierId: Long,
        @JsonProperty("total_cost") val totalCost: Double,
        @JsonProperty("dispatched_items") val dispatchedItems: DispatchedItemsRequest)

data class DispatchedItemsRequest(
        @JsonProperty("items") val items: List<DispatchedItemRequest>)

data class DispatchedItemRequest(
        @JsonProperty("sku") val sku: String,
        @JsonProperty("amount") val amount: Int,
        @JsonProperty("unit_price") val unitPrice: Double,
        @JsonProperty("subtotal") val subtotal: Double)
