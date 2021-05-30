package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CashResponse(@JsonProperty("CASH_ID") val id: Long,
                        @JsonProperty("BRANCH_ID") val branchId: Long,
                        @JsonProperty("POINT_OF_SALE") val pointOfSale: Long,
                        @JsonProperty("STATUS") var status: String)
