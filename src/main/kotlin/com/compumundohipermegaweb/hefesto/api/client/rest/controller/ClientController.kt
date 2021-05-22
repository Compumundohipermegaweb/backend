package com.compumundohipermegaweb.hefesto.api.client.rest.controller

import com.compumundohipermegaweb.hefesto.api.client.domain.action.FindClients
import com.compumundohipermegaweb.hefesto.api.client.domain.action.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ActionData
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.client.rest.response.ClientResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        return ClientResponse(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }
}