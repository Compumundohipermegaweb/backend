package com.compumundohipermegaweb.hefesto.api.checking.account.domain.model

data class CheckingAccount(val id: Long,
                           val clientId: Long,
                           var creditLimit: Double,
                           var balanceDue: Double,
                           var balance: Double)
