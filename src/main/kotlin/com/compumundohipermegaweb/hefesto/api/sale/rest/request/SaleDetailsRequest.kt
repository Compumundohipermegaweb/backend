package com.compumundohipermegaweb.hefesto.api.sale.rest.request

import com.fasterxml.jackson.annotation.JsonProperty

data class SaleDetailsRequest(@JsonProperty("sale_details") val saleDetailsRequest: List<SaleDetailRequest>)