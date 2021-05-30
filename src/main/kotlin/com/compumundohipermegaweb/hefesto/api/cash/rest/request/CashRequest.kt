package com.compumundohipermegaweb.hefesto.api.cash.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CashRequest(@JsonProperty("cash_id") val id: Long,
                       @JsonProperty("branch_id") val branchId: Long,
                       @JsonProperty("point_of_sale") val pointOfSale: Long,
                       @JsonProperty("status") var status: String)
