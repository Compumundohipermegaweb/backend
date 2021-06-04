package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import java.util.*

data class Income(val movement_id: Long,
                  val datetime: Date,
                  val transactionId: Long,
                  val transactionDescription: String,
                  val detail: String,
                  val payments: List<String>,
                  val amount: Double)