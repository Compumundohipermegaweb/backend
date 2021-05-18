package com.compumundohipermegaweb.hefesto.api.sale.rest.response

import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class InvoiceResponse(@JsonProperty("invoice_id") val invoiceId: String,
                           @JsonProperty("billing_date") val billingDate: String,
                           @JsonProperty("type") val type: String,
                           @JsonProperty("client") val client: ClientResponse,
                           @JsonProperty("branch_address") val branchAddress: String,
                           @JsonProperty("branch_contact") val branchContact: String,
                           @JsonProperty("cuit") val cuit: String,
                           @JsonProperty("activity_since") val activitySince: String,
                           @JsonProperty("sale_details") val saleDetails: SaleDetailsResponse,
                           @JsonProperty("subtotal") val subtotal: Double,
                           @JsonProperty("iva_subtotal") val ivaSubtotal: Double,
                           @JsonProperty("total") val total: Double)
