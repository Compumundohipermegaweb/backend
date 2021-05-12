package com.compumundohipermegaweb.hefesto.api.client.domain.model


data class Client( val id: Long,
                   val documentNumber: String,
                   val firstName: String,
                   val lastName: String,
                   val state: String,
                   val creditLimit: Double,
                   val email: String,
                   val contactNumber: String)
