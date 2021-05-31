package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class IncomeResponse(@JsonProperty("id_movement") val movement_id: Long,
                          @JsonProperty("datetime") val datetime: Date,
                          @JsonProperty("transaction") val transactionDescription: String,
                          @JsonProperty("detail") val detail: String,
                          @JsonProperty("payment") val payments: String,
                          @JsonProperty("amount") val amount: Double)

@JsonIgnoreProperties(ignoreUnknown = true)
data class IncomesResponse(@JsonProperty("incomes") val incomes: List<IncomeResponse>)