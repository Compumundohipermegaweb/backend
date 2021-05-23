package com.compumundohipermegaweb.hefesto.api.client.rest.controller

import com.compumundohipermegaweb.hefesto.api.branch.rest.representation.StockAvailableResponse
import com.compumundohipermegaweb.hefesto.api.client.domain.action.FindClients
import com.compumundohipermegaweb.hefesto.api.client.domain.action.GetBalanceByClientId
import com.compumundohipermegaweb.hefesto.api.client.domain.action.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ActionData
import com.compumundohipermegaweb.hefesto.api.client.rest.request.ClientRequest
import com.compumundohipermegaweb.hefesto.api.client.rest.response.ClientBalanceResponse
import com.compumundohipermegaweb.hefesto.api.client.rest.response.ClientResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clients")
class ClientController(private val registerClient: RegisterClient,
                       private val findClients: FindClients,
                       private val getBalanceByClientId: GetBalanceByClientId) {

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
    @GetMapping("/{CLIENT_ID}/checking-account/balance")
    fun getStockAvailable (@PathVariable("CLIENT_ID") clientId: Long): ResponseEntity<ClientBalanceResponse> {
        val checkingAccount = getBalanceByClientId.invoke(clientId)
        if (checkingAccount != null) {
            return ResponseEntity.ok(ClientBalanceResponse(checkingAccount.id,checkingAccount.balance))
        }
        return    ResponseEntity.noContent().build()
    }

    private fun Client.toClientResponse(): ClientResponse {
        return ClientResponse(id, documentNumber, firstName, lastName, state, creditLimit, email, contactNumber)
    }


}