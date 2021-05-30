package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import java.util.*

data class CashStartEnd(val id: Long,
                        val cashId: Long,
                        val openDate: Date,
                        val openingBalance: Double,
                        val userId: Long,
                        val closeDate: Date,
                        val realBalance: Double,
                        val theoreticalBalance: Double,
                        val date: Date)