package com.compumundohipermegaweb.hefesto.api.branch.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetStockRequest(
        @JsonProperty("category_id") val categoryId: Long?,
        @JsonProperty("description") val description: String?,
        @JsonProperty("brand_id") val brandId: Long?,
        @JsonProperty("imported") val imported: Boolean?)
