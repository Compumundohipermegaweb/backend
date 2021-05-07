package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.compumundohipermegaweb.hefesto.api.client.rest.ClientResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class InvoiceResponse(@JsonProperty("id") val id: Long,
                           @JsonProperty("type") val type: String,
                           @JsonProperty("client_response") val clientResponse: ClientResponse,
                           @JsonProperty("branch_response") val branchResponse: BranchResponse,
                           @JsonProperty("items") val items: ShortItemsResponse,
                           @JsonProperty("sub_total") val subTotal: Double,
                           @JsonProperty("total") val total: Double,
                           @JsonProperty("iva") val iva: Double,
                           @JsonProperty("short_payments_details") val shortPaymentsDetails: ShortPaymentsResponse
)