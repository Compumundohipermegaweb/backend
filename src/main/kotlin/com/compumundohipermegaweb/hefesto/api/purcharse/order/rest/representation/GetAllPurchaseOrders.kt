package com.compumundohipermegaweb.hefesto.api.purcharse.order.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class GetAllPurchaseOrders(
        @JsonProperty("purchase_orders") val purchaseOrders: List<PurchaseOrderResponse>)

data class PurchaseOrderResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("branch_id") val branchId: Long,
        @JsonProperty("sku") val sku: String,
        @JsonProperty("amount") val amount: Int,
        @JsonProperty("supplier") val supplier: String,
        @JsonProperty("status") val status: String,
        @JsonProperty("dispatch_id") val dispatchId: Long)
