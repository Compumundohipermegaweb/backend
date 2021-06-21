package com.compumundohipermegaweb.hefesto.api.report.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OnlineSaleVsLocalSaleDataResponse(@JsonProperty("sales_type") val salesType: List<String>,
                                             @JsonProperty("sales_quantity") val salesQuantity: List<Int>,
                                             @JsonProperty("sales_amount") val salesAmount: List<Double>)
