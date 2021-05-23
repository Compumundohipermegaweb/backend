package com.compumundohipermegaweb.hefesto.api.stock.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class StocksResponse(@JsonProperty("stocks") val stocksResponse: List<StockedResponse>)