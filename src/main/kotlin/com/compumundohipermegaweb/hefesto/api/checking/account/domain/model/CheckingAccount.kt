package com.compumundohipermegaweb.hefesto.api.checking.account.domain.model

data class CheckingAccount(val id: Long,
                           val clientId: Long,
                           val creditLimit: Double,
                           val balanceDue: Double,
                           val balance: Double)
