package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.compumundohipermegaweb.hefesto.api.client.rest.ClientRequest
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleRequest(@JsonProperty("type") val type: String,
                       @JsonProperty("client_request") val clientRequest: ClientRequest,
                       @JsonProperty("id_salesman") val idSalesman: Long,
                       @JsonProperty("id_branch") val idBranch: Long,
                       @JsonProperty("sale_details") val saleDetailsRequest: SaleDetailsRequest)
