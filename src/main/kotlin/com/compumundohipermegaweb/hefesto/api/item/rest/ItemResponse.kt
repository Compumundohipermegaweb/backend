package com.compumundohipermegaweb.hefesto.api.item.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemResponse(@JsonProperty("id") val id: Long,
                        @JsonProperty("sku") val sku: String,
                        @JsonProperty("short_description") val shortDescription: String,
                        @JsonProperty("long_description") val longDescription: String,
                        @JsonProperty("measure") val measure: String,
                        @JsonProperty("stock_total") val stockTotal: Int,
                        @JsonProperty("minimum_stock") val minimumStock: Int,
                        @JsonProperty("security_stock") val securityStock: Int,
                        @JsonProperty("supplier") val supplier: String,
                        @JsonProperty("cort") val cost: Double,
                        @JsonProperty("unit_price") val unitPrice: Double)
