package com.compumundohipermegaweb.hefesto.api.stock.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class StockToReduceRequest(@JsonProperty("item_id") val itemId: Long,
                                @JsonProperty("amount") val amount: Int)
