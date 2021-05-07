package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.compumundohipermegaweb.hefesto.api.client.rest.ClientRequest
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class InvoiceRequest(@JsonProperty("type") val type: String,
                          @JsonProperty("client_request") val clientRequest: ClientRequest,
                          @JsonProperty("id_salesman") val idSalesman: Long,
                          @JsonProperty("id_branch") val idBranch: Long,
                          @JsonProperty("items_request") val itemsRequest: ShortItemsRequest,
                          @JsonProperty("total") val total: Double,
                          @JsonProperty("payments_details_request") val paymentsDetailsRequest: ShortPaymentsRequest)
