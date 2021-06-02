package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import java.util.*

data class Expense(val movement_id: Long,
              val datetime: Date,
              val transactionDescription: String,
              val detail: String,
              val payments: String,
              val amount: Double)