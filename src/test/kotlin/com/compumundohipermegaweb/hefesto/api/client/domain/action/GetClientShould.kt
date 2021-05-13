package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.rest.ActionData
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class GetClientShould {

    private lateinit var clientRepository: ClientRepository
    private lateinit var getClient: GetClient

    private lateinit var foundClients: List<Client>


    @Test
    fun `find clients by name`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = CLIENT_1.firstName, document = null))

        then(foundClients).contains(CLIENT_1, CLIENT_2)
    }

    @Test
    fun `find clients by full name`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = "${CLIENT_3.firstName} ${CLIENT_3.lastName}", document = null))

        then(foundClients).contains(CLIENT_2, CLIENT_3)
    }

    @Test
    fun `find clients by document`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(document = CLIENT_1.documentNumber, name = null))

        then(foundClients).containsOnly(CLIENT_1)
    }

    @Test
    fun `find clients by name and document`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = CLIENT_1.firstName, document = CLIENT_1.documentNumber ))

        then(foundClients).containsOnly(CLIENT_1)
    }

    @Test
    fun `return empty if the document and name does not match to the same client`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = CLIENT_1.firstName, document = CLIENT_3.documentNumber))

        then(foundClients).isEmpty()
    }

    private fun givenClientRepository() {
        clientRepository = mock()
        whenever(clientRepository.findByFirstNameOrLastNameIn(listOf(CLIENT_1.firstName))).thenReturn(listOf(CLIENT_1, CLIENT_2))
        whenever(clientRepository.findByFirstNameOrLastNameIn(listOf(CLIENT_3.firstName, CLIENT_3.lastName))).thenReturn(listOf(CLIENT_2, CLIENT_3))
        whenever(clientRepository.findByDocument(CLIENT_1.documentNumber)).thenReturn(listOf(CLIENT_1))
        whenever(clientRepository.findByDocument(CLIENT_3.documentNumber)).thenReturn(listOf(CLIENT_3))
    }

    private fun givenGetClient() {
        getClient = GetClient(clientRepository)
    }

    private fun whenGettingClient(with: ActionData) {
        foundClients = getClient(with)
    }

    private companion object {
        val CLIENT_1 = Client(0L, "00000000","Name", "Sar", "", 0.0, "", "")
        val CLIENT_2 = Client(1L, "11111111","Name", "Lastname", "", 0.0, "", "")
        val CLIENT_3 = Client(2L, "22222222","Mister", "Lastname", "", 0.0, "", "")

    }
}