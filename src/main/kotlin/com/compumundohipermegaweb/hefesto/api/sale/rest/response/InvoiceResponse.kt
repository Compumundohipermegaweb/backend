package com.compumundohipermegaweb.hefesto.api.sale.rest.response

import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class InvoiceResponse(@JsonProperty("") val id: Long,
                           @JsonProperty("") val billingDate: String,
                           @JsonProperty("") val type: String,
                           @JsonProperty("") val client: ClientResponse,
                           @JsonProperty("") val branchAddress: String,
                           @JsonProperty("") val branchContact: String,
                           @JsonProperty("") val cuit: String,
                           @JsonProperty("") val activitySince: String,
                           @JsonProperty("") val saleDetails: SaleDetailsResponse,
                           @JsonProperty("") val subTotal: Double,
                           @JsonProperty("") val ivaSubTotal: Double,
                           @JsonProperty("") val total: Double)
