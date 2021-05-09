package com.compumundohipermegaweb.hefesto.api.sale.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SaleDetailsResponse(@JsonProperty("sale_details") val saleItemDetailsResponse: List<SaleItemDetailResponse>)
