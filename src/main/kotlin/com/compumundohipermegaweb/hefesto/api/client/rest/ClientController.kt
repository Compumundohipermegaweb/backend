package com.compumundohipermegaweb.hefesto.api.client.rest

import com.compumundohipermegaweb.hefesto.api.client.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/clients")
class ClientController(private val registerClient: RegisterClient) {

    @PostMapping
    fun postClient(clientRequest: ClientRequest) : ResponseEntity<ClientResponse> {
        return ResponseEntity.ok(registerClient(clientRequest).toClientResponse())
    }

    private fun Client.toClientResponse(): ClientResponse {
        return ClientResponse(documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }
}