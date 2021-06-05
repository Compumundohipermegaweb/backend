package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import java.util.*

data class CashMovement(
    val id: Long,
    val cashStartEndId: Long,
    val movementType: String,
    val dateTime: Date,
    val sourceId: Long,
    val sourceDescription: String,
    var paymentMethodId: Long,
    val transactionId: Long,
    val userId: Long,
    val amount: Double,
    val detail: String
)
