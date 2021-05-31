package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import java.util.*

data class CashMovement(val id: Long,
                        val cashStartEndId: Long,
                        val movementType: String,
                        val dateTime: Date,
                        val transactionId: Long,
                        val paymentMethodId: Long,
                        val cardId: Long,
                        val userId: Long,
                        val amount: Double,
                        val detail: String
)
