package com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.model

data class PaymentMethod (
    val id: Long,
    val paymentMethod: String,
    val state: String
    )