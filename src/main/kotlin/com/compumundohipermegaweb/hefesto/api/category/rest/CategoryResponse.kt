package com.compumundohipermegaweb.hefesto.api.category.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CategoryResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("name") val name: String,
        @JsonProperty("description") val description: String)

data class FindAllCategoriesResponse(
        @JsonProperty("categories") val categories: List<CategoryResponse>)