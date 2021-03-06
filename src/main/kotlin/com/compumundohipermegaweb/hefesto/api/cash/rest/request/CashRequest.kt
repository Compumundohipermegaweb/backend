package com.compumundohipermegaweb.hefesto.api.cash.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CashRequest(@JsonProperty("branch_id") val branchId: Long)
