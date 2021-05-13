package com.compumundohipermegaweb.hefesto.api.client.rest.controller

import com.compumundohipermegaweb.hefesto.api.client.domain.action.FindClients
import com.compumundohipermegaweb.hefesto.api.client.domain.action.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ActionData
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientRequest
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["https://hefesto-dev.web.app", "http://localhost:4200"])
@RestController
@RequestMapping("/api/clients")
class ClientController(private val registerClient: RegisterClient, private val findClients: FindClients) {

    @PostMapping
    fun postClient(@RequestBody clientRequest: ClientRequest) : ResponseEntity<ClientResponse> {
        return ResponseEntity.ok(registerClient(clientRequest).toClientResponse())
    }

    @GetMapping
    fun getClient(@RequestParam("document") document: String?,
                  @RequestParam("name") name: String?): ResponseEntity<List<ClientResponse>> {
        val actionData = ActionData(name, document)
        val clients = findClients(actionData)
        return if (clients.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(clients.map { it.toClientResponse() })
        }
    }

    private fun Client.toClientResponse(): ClientResponse {
        return ClientResponse(documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }
}