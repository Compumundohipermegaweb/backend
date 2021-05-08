package com.compumundohipermegaweb.hefesto.api.item.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemsResponse(@JsonProperty("found_items") val foundItems: List<ItemResponse>)
