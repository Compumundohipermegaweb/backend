package com.compumundohipermegaweb.hefesto.api.branch.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class StockResponse(
        @JsonProperty("items") val items: List<ItemStockResponse>
)

data class ItemStockResponse(
        @JsonProperty("sku") val sku: String,
        @JsonProperty("short_description") val shortDescription: String,
        @JsonProperty("long_description") val longDescription: String,
        @JsonProperty("brand_name") val brand: String,
        @JsonProperty("unit_price") val price: Double,
        @JsonProperty("available_stock") val availableStock: Int,
        @JsonProperty("imported") val imported: Boolean,
        @JsonProperty("category") val category: Long)
