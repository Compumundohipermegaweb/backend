package com.compumundohipermegaweb.hefesto.api.branch.rest.representation

import com.fasterxml.jackson.annotation.JsonProperty

data class BranchResponse(
        @JsonProperty("id") val id: Long,
        @JsonProperty("name") val branch: String,
        @JsonProperty("address") val address: String,
        @JsonProperty("postal_code") val postalCode: String,
        @JsonProperty("email") val email: String,
        @JsonProperty("contact_number") val contactNumber: String,
        @JsonProperty("attention_schedule") val attentionSchedule: String)
