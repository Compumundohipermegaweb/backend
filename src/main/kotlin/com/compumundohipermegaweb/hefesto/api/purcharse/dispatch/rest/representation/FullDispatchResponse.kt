package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation

import com.compumundohipermegaweb.hefesto.api.purcharse.order.rest.representation.PurchaseOrderResponse
import com.compumundohipermegaweb.hefesto.api.supplier.rest.representation.SupplierResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class FullDispatchResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("supplier") val supplier: SupplierResponse,
        @JsonProperty("total") val totalCost: Double,
        @JsonProperty("status") val status: String,
        @JsonProperty("purchase_orders") val purchaseOrders: List<PurchaseOrderResponse>)
