package com.compumundohipermegaweb.hefesto.api.stock.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class StockRequest(@JsonProperty("branch_id") val branchId: Long,
                        @JsonProperty("stock_total") val stockTotal: Int,
                        @JsonProperty("minimum_stock") val minimumStock: Int,
                        @JsonProperty("security_stock") val securityStock: Int)
