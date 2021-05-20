package com.compumundohipermegaweb.hefesto.api.client

import com.compumundohipermegaweb.hefesto.api.client.domain.action.RegisterClient
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ClientRequest
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RegisterClientShould {

    private lateinit var clientService: ClientService
    private lateinit var registerClient: RegisterClient
    private lateinit var registeredClient: Client

    @Test
    fun `register a client`() {
        givenClientService()
        givenRegisterClient()

        whenRegisteringClient(CLIENT_REQUEST)

        thenTheClientIsSuccessfullyRegister()
    }

    private fun givenClientService() {
        clientService = mock(ClientService::class.java)
        `when`(clientService.save(CLIENT)).thenReturn(CLIENT)
    }

    private fun givenRegisterClient() {
        registerClient = RegisterClient(clientService)
    }

    private fun whenRegisteringClient(client: ClientRequest) {
        registeredClient = registerClient(client)
    }

    private fun thenTheClientIsSuccessfullyRegister() {
        verify(clientService).save(CLIENT)
        then(registeredClient).isNotNull
        then(registeredClient.id).isEqualTo(0L)
    }

    private companion object {
        private val CLIENT = Client(0L, "", "", "", "", 0.0, "", "")
        val CLIENT_REQUEST = ClientRequest(0L, "", "", "", "",0.0,"", "")

    }
}