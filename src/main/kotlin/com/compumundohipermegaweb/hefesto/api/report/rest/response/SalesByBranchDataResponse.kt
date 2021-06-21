package com.compumundohipermegaweb.hefesto.api.report.rest.response

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SalesByBranchDataResponse(@JsonProperty("branches") val branches: List<Branch>,
                                     @JsonProperty("sales_quantity") val salesQuantity: List<Int>)
