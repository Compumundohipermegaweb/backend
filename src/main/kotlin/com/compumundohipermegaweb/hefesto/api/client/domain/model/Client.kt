package com.compumundohipermegaweb.hefesto.api.client.domain.model


data class Client( val id: Long,
                   val documentNumber: String,
                   val firstName: String,
                   val lastName: String,
                   val surName: String,
                   var category: String,
                   var email: String,
                   var contactNumber: String)
