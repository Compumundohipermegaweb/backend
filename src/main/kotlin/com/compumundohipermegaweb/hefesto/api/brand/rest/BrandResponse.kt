package com.compumundohipermegaweb.hefesto.api.brand.rest

import com.fasterxml.jackson.annotation.JsonProperty

data class BrandResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("name") val name: String)

data class FindAllBrandsResponse(
        @JsonProperty("brands") val brands: List<BrandResponse>)