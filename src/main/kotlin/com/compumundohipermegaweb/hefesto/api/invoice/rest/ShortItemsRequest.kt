package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.compumundohipermegaweb.hefesto.api.invoice.domain.model.ShortItem
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShortItemsRequest(@JsonProperty("items") val items: List<ShortItem>)