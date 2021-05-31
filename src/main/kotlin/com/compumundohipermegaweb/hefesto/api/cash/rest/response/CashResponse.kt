package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.compumundohipermegaweb.hefesto.api.category.rest.CategoryResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CashResponse(@JsonProperty("cash_id") val id: Long,
                        @JsonProperty("branch_id") val branchId: Long,
                        @JsonProperty("point_of_sale") val pointOfSale: Long,
                        @JsonProperty("status") var status: String)

data class FindAllCashResponse(
    @JsonProperty("cash_registers") val cash_registers: List<CashResponse>)
