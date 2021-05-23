package com.compumundohipermegaweb.hefesto.api.stock.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class StocksToReduceRequest(@JsonProperty("reduce_all") val stock: List<StockToReduceRequest>)
