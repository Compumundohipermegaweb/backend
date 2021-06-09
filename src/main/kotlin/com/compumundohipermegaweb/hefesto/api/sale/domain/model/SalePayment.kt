package com.compumundohipermegaweb.hefesto.api.sale.domain.model

data class SalePayment(val id: Long,
                       val saleId: Long,
                       val paymentMethodId: Long,
                       val cardId: Long?,
                       val lastDigits: String?,
                       val email: String?,
                       val subTotal: Double)
