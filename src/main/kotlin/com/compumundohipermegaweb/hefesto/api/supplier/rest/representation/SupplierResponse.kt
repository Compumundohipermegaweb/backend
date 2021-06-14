package com.compumundohipermegaweb.hefesto.api.supplier.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class SupplierResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("organization") val organization: String,
        @JsonProperty("contact_name") val contactName: String,
        @JsonProperty("contact_number") val contactNumber: String,
        @JsonProperty("email") val email: String,
        @JsonProperty("cuit") val cuit: String)