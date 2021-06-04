package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.compumundohipermegaweb.hefesto.api.cash.domain.model.CashMovement
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PaySaleRequest(@JsonProperty("cash_movement") val cashMovement: CashMovement,
                          @JsonProperty("payments_details") val paymentDetails: List<PaymentRequest>)
