package com.compumundohipermegaweb.hefesto.api.item.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemResponse(@JsonProperty("id")  val id: Long,
                        @JsonProperty("sku")  val sku: String,
                        @JsonProperty("short_description") val shortDescription: String,
                        @JsonProperty("description") val description: String,
                        @JsonProperty("category_id")  val categoryId: Long,
                        @JsonProperty("uom_sale")  val uomSale: String,
                        @JsonProperty("price")  val price: Double,
                        @JsonProperty("cost")  val cost: Double,
                        @JsonProperty("imported")  val imported: Boolean,
                        @JsonProperty("state")  val state: String)
