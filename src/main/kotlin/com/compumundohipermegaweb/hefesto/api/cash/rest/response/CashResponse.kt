package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CashResponse(@JsonProperty("cash_id") val id: Long,
                        @JsonProperty("branch_id") val branchId: Long,
                        @JsonProperty("point_of_sale") val pointOfSale: Long,
                        @JsonProperty("status") var status: String)

data class CashRegisters(
    @JsonProperty("cash_registers") val cash_registers: List<CashResponse>)

data class CashMovementResponse(
    @JsonProperty("id") val id: Long,
    @JsonProperty("cash_start_end_id") val cashStartEndId: Long,
    @JsonProperty("movement_type") val movementType: String,
    @JsonProperty("source_id") val sourceId: Long,
    @JsonProperty("source_description") val sourceDescription: String,
    @JsonProperty("user_id") val userId: Long,
    @JsonProperty("amount") val amount: Double,
    @JsonProperty("detail") val detail: String
)