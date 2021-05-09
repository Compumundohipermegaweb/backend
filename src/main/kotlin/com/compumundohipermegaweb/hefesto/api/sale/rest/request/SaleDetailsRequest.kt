package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleDetailsRequest(@JsonProperty("item_details") val itemDetailsRequest: List<ItemDetailRequest>,
                              @JsonProperty("payment_details") val paymentDetailsRequest: List<PaymentDetailRequest>)