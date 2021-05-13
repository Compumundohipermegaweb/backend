package com.compumundohipermegaweb.hefesto.api.client.domain.action

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.rest.representation.ActionData
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class FindClientsShould {

    private lateinit var clientRepository: ClientRepository
    private lateinit var findClients: FindClients

    private lateinit var foundClients: List<Client>


    @Test
    fun `find clients by name`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = CLIENT_1.firstName, document = null))

        thenClientsWhereFound(CLIENT_1, CLIENT_2)
    }

    @Test
    fun `find clients by full name`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = "${CLIENT_3.firstName} ${CLIENT_3.lastName}", document = null))

        thenClientsWhereFound(CLIENT_2, CLIENT_3)
    }

    @Test
    fun `find clients by document`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(document = CLIENT_1.documentNumber, name = null))

        thenOnlyFoundClient(CLIENT_1)
    }

    @Test
    fun `find clients by name and document`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = CLIENT_1.firstName, document = CLIENT_1.documentNumber ))

        thenOnlyFoundClient(CLIENT_1)
    }

    @Test
    fun `return empty if the document and name does not match to the same client`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = CLIENT_1.firstName, document = CLIENT_3.documentNumber))

        thenNoClientsWhereFound()
    }

    @Test
    fun `return empty if no document matches`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = null, document = UNKNOWN_DOCUMENT))

        thenNoClientsWhereFound()
    }

    @Test
    fun `return empty if no names matches`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = UNKNOWN_NAME, document = null))

        thenNoClientsWhereFound()
    }

    @Test
    fun `ignore null or blank names`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = "  ", document = CLIENT_1.documentNumber))

        thenClientsWhereFound(CLIENT_1)
    }

    @Test
    fun `ignore null or blank documents`() {
        givenClientRepository()
        givenGetClient()

        whenGettingClient(with = ActionData(name = CLIENT_1.firstName, document = "    "))

        thenClientsWhereFound(CLIENT_1)
    }

    private fun givenClientRepository() {
        clientRepository = mock()
        `when`(clientRepository.findByFirstNameOrLastNameIn(listOf(CLIENT_1.firstName))).thenReturn(listOf(CLIENT_1, CLIENT_2))
        `when`(clientRepository.findByFirstNameOrLastNameIn(listOf(CLIENT_3.firstName, CLIENT_3.lastName))).thenReturn(listOf(CLIENT_2, CLIENT_3))
        `when`(clientRepository.findByDocument(CLIENT_1.documentNumber)).thenReturn(CLIENT_1)
        `when`(clientRepository.findByDocument(CLIENT_3.documentNumber)).thenReturn(CLIENT_3)
        `when`(clientRepository.findByDocument(UNKNOWN_DOCUMENT)).thenReturn(null)
        `when`(clientRepository.findByFirstNameOrLastNameIn(listOf(UNKNOWN_NAME))).thenReturn(emptyList())
    }

    private fun givenGetClient() {
        findClients = FindClients(clientRepository)
    }

    private fun whenGettingClient(with: ActionData) {
        foundClients = findClients(with)
    }

    private fun thenClientsWhereFound(vararg clients: Client) {
        then(foundClients).contains(*clients)
    }

    private fun thenOnlyFoundClient(client: Client) {
        then(foundClients).containsOnly(client)
    }

    private fun thenNoClientsWhereFound() {
        then(foundClients).isEmpty()
    }

    private companion object {
        const val UNKNOWN_DOCUMENT = "40060441"
        const val UNKNOWN_NAME = "Unknown Name"
        val CLIENT_1 = Client(0L, "00000000","Name", "Sar", "", 0.0, "", "")
        val CLIENT_2 = Client(1L, "11111111","Name", "Lastname", "", 0.0, "", "")
        val CLIENT_3 = Client(2L, "22222222","Mister", "Lastname", "", 0.0, "", "")

    }
}