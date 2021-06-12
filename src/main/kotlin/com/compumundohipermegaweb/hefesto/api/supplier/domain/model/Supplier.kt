package com.compumundohipermegaweb.hefesto.api.supplier.domain.model

data class Supplier(
    val id: Long,
    val organization: String,
    val contactName: String,
    val contactNumber: String,
    val email: String,
    val cuit: String,
    val supplySku: String)


