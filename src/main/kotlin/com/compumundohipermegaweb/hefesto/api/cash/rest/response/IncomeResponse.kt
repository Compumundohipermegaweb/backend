package com.compumundohipermegaweb.hefesto.api.cash.rest.response

import com.compumundohipermegaweb.hefesto.api.client.rest.response.ClientResponse
import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class IncomeResponse(@JsonProperty("id_movement") val movementId: Long,
                          @JsonProperty("datetime") val datetime: Date,
                          @JsonProperty("source_id") val sourceId: Long,
                          @JsonProperty("source_description") val sourceDescription: String,
                          @JsonProperty("detail") val detail: String,
                          @JsonProperty("payments") val payments: List<SalePayment>,
                          @JsonProperty("amount") val amount: Double,
                          @JsonProperty("salesman_id") val salesmanId: Long,
                          @JsonProperty("client") val clientResponse: ClientResponse?,
                          @JsonProperty("transaction_id") val transactionId: Long
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class IncomesResponse(@JsonProperty("incomes") val incomes: List<IncomeResponse>)