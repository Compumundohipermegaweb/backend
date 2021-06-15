package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ConfirmDispatchRequest(
        @JsonProperty("branch_id") val branchId: Long,
        @JsonProperty("total_cost") val totalCost: Double)
