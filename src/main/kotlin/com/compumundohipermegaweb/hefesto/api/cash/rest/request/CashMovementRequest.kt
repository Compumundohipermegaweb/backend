package com.compumundohipermegaweb.hefesto.api.cash.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class CashMovementRequest(
    @JsonProperty("cash_start_end_id") val cashStartEndId: Long,
    @JsonProperty("movement_type") val movementType: String,
    @JsonProperty("source_id") val sourceId: Long,
    @JsonProperty("source_description") val sourceDescription: String,
    @JsonProperty("user_id") val userId: Long,
    @JsonProperty("amount") val amount: Double,
    @JsonProperty("detail") val detail: String
)


