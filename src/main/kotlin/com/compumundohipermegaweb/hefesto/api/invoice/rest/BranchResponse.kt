package com.compumundohipermegaweb.hefesto.api.invoice.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BranchResponse(@JsonProperty("adress") val address: String,
                          @JsonProperty("contact") val contact: String,
                          @JsonProperty("cuit") val cuit: String,
                          @JsonProperty("gross_income") val grossIncome: String,
                          @JsonProperty("ativity_since") val activitySince: String)

