package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleRequest(@JsonProperty("invoice_type") val invoiceType: String,
                       @JsonProperty("client") val clientRequest: ClientRequest,
                       @JsonProperty("salesman_id") val salesmanId: Long,
                       @JsonProperty("branch_id") val branchId: Long,
                       @JsonProperty("sale_details") val saleDetailsRequest: SaleDetailsRequest,
                       @JsonProperty("category") val category: String)
