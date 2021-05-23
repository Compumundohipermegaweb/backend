package com.compumundohipermegaweb.hefesto.api.stock.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class StockedResponse(@JsonProperty("id") val id: Long,
                           @JsonProperty("sku") val sku: String,
                           @JsonProperty("stock_total") var stockTotal: Int,
                           @JsonProperty("minimum_stock") val minimumStock: Int,
                           @JsonProperty("security_stock") val securityStock: Int)