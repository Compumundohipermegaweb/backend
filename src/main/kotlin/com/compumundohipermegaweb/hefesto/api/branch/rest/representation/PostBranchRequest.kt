package com.compumundohipermegaweb.hefesto.api.branch.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PostBranchRequest (
    @JsonProperty("id") val id : Long,
    @JsonProperty("branch") val branch : String,
    @JsonProperty("address") val address : String,
    @JsonProperty("postal_code") val postalCode : String,
    @JsonProperty("email") val email : String,
    @JsonProperty("contact_number") val contactNumber : String,
    @JsonProperty("attention_schedule") val attentionSchedule : String
)
