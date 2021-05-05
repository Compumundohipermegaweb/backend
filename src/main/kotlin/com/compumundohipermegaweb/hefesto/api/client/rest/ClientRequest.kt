package com.compumundohipermegaweb.hefesto.api.client.rest

data class ClientRequest(val documentNumber: String,
                         val firstName: String,
                         val lastName: String,
                         val surName: String,
                         var category: String,
                         var email: String,
                         var contactNumber: String)