package com.compumundohipermegaweb.hefesto.api.payment.method.domain.model

data class PaymentMethod (val id: Long,
                          val description: String,
                          val state: String) {

    enum class Type {
        EFECTIVO, TARJETA, CUENTA_CORRIENTE, ONLINE, BANCARIO
    }
}