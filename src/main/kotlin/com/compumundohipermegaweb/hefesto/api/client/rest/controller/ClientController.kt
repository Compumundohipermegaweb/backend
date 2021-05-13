package com.compumundohipermegaweb.hefesto.api.client.rest.controller

import com.compumundohipermegaweb.hefesto.api.client.domain.action.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientRequest
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientResponse
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