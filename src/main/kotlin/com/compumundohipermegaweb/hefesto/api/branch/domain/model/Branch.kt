package com.compumundohipermegaweb.hefesto.api.branch.domain.model

data class Branch (
    val id : Long,
    val branch : String,
    val address : String,
    val postalCode : String,
    val email : String,
    val contactNumber : String,
    val attentionSchedule : String
)
