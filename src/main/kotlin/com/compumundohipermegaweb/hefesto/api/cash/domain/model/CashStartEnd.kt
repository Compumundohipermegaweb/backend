package com.compumundohipermegaweb.hefesto.api.cash.domain.model

import java.util.*

data class CashStartEnd(
    val id: Long,
    val cashId: Long,
    val openDate: Date,
    val openingBalance: Double,
    val userId: Long,
    var closeDate: Date?,
    var realBalance: Double,
    var theoreticalBalance: Double,
    val date: Date)