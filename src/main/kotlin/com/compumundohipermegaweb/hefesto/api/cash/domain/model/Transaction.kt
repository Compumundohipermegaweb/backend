package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import java.util.*

data class Transaction(val movement_id: Long,
                       val datetime: Date,
                       val transactionDescription: String,
                       val detail: String,
                       val payments: String,
                       val amount: Double)