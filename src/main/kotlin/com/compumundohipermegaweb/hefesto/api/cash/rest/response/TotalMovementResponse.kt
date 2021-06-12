package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class TotalMovementResponse (
    @JsonProperty("branch_id") val branchId: Long,
    @JsonProperty("cash_id") val cashId: Long,
    @JsonProperty("cash_start_end_id") val cashStartEndId: Long,
    @JsonProperty("date") val dateTime: String,
    @JsonProperty("movement_type") val movementType: String,
    @JsonProperty("payment_method") val paymentMethod: String,
    @JsonProperty("total") val total: Double
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class TotalsMovementResponse(@JsonProperty("totals") val totals: List<TotalMovementResponse>)
