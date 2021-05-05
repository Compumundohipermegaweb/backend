package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.model.ClientRepository
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RegisterClientShould {
    private lateinit var registerClient: RegisterClient
    private lateinit var clientRepository: ClientRepository
    private lateinit var registeredClient: Client

    @Test
    fun `register a client`() {
        givenClientRepository()
        givenRegisterClient()

        whenRegisteringClient(CLIENT)

        thenTheClientIsSuccessfullyRegister()
    }

    private fun givenClientRepository() {
        clientRepository = mock(ClientRepository::class.java)
        `when`(clientRepository.save(CLIENT)).thenReturn(CLIENT)
    }

    private fun givenRegisterClient() {
        registerClient = RegisterClient(clientRepository)
    }

    private fun whenRegisteringClient(client: Client) {
        registeredClient = registerClient(client)
    }

    private fun thenTheClientIsSuccessfullyRegister() {
        then(registeredClient).isNotNull
    }

    private companion object {
        private val CLIENT = Client(0L, "", "", "", "", "", "", "")
    }
}