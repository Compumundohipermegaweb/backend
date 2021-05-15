package com.compumundohipermegaweb.hefesto.api.item.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemResponse(@JsonProperty("sku")  val sku: String,
                        @JsonProperty("short_description") val shortDescription: String,
                        @JsonProperty("description") val escription: String,
                        @JsonProperty("brand_id")  val brandId: Long,
                        @JsonProperty("category_id")  val categoryId: Long,
                        @JsonProperty("uom_sale")  val uomSale: String,
                        @JsonProperty("price")  val price: Double,
                        @JsonProperty("imported")  val imported: Boolean,
                        @JsonProperty("state")  val state: String)
