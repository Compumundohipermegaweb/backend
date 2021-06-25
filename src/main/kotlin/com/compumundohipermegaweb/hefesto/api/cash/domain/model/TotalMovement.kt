package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import java.util.*

class TotalMovement (
    val branchId: Long,
    val cashId: Long,
    val cashStartEndId: Long,
    val dateTime: Date,
    val movementType: String,
    val source: String,
    val paymentMethod: String,
    val card: String?,
    val digits: String?,
    val detail: String?,
    val total: Double,
    val level: Long
)


