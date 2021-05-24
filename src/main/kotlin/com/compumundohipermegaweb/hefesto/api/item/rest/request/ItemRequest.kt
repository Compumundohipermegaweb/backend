package com.compumundohipermegaweb.hefesto.api.item.rest.request

import com.compumundohipermegaweb.hefesto.api.supplier.rest.representation.PostSupplierRequest
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemRequest(@JsonProperty("sku")  val sku: String,
                       @JsonProperty("short_description") val shortDescription: String,
                       @JsonProperty("description") val description: String,
                       @JsonProperty("brand_id")  val brandId: Long,
                       @JsonProperty("category_id")  val categoryId: Long,
                       @JsonProperty("uom_sale")  val uomSale: String,
                       @JsonProperty("price")  val price: Double,
                       @JsonProperty("cost")  val cost: Double,
                       @JsonProperty("imported")  val imported: Boolean,
                       @JsonProperty("state")  val state: String,
                       @JsonProperty("supplier")  val supplier: PostSupplierRequest
)