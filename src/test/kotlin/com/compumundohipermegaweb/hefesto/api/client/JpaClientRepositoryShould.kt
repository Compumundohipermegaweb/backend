package com.compumundohipermegaweb.hefesto.api.client

import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infra.repository.JpaClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infra.repository.SpringDataClientRepository
import com.compumundohipermegaweb.hefesto.api.client.infra.representation.ClientRepresentation
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

class JpaClientRepositoryShould {

    private lateinit var springDataClientRepository: SpringDataClientRepository
    private lateinit var clientRepository: ClientRepository

    private lateinit var clientSaved: Client
    private var clientFound: Client? = null
    private lateinit var clientsFound: List<Client>

    @Test
    fun `save the client`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenSavingTheClient()

        thenInputSaved()
    }

    @Test
    fun `find client by name`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientsByName(CLIENT.firstName, CLIENT.lastName)

        thenClientsWhereFound()
    }

    @Test
    fun `find client by document`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientByDocumentNumber(CLIENT.documentNumber)

        thenClientHasBeenFound()
    }

    @Test
    fun `return null if no client matches de document`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientByDocumentNumber(RANDOM_DOCUMENT_NUMBER)

        thenNoClientWhereFound()
    }

    @Test
    fun `return empty if no clients can be found by name`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientsByName(RANDOM_FIRST_NAME, RANDOM_LAST_NAME)

        thenNoClientsWhereFound()
    }

    @Test
    fun `find client by id`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientById(CLIENT.id)

        thenClientHasBeenFoundById()
    }

    @Test
    fun `return the found client by id`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientById(CLIENT.id)

        thenTheFoundClientIsReturned()
    }

    @Test
    fun `not find client by id`() {
        givenSpringDataClientRepository()
        givenClientRepository()

        whenFindingClientById(1L)

        thenClientHasNotFoundById()
    }

    private fun givenSpringDataClientRepository() {
        springDataClientRepository = mock(SpringDataClientRepository::class.java)
        `when`(springDataClientRepository.save(CLIENT_DAO)).thenReturn(CLIENT_DAO)
        `when`(springDataClientRepository.findAllByFirstOrLastName(listOf(CLIENT.firstName, CLIENT.lastName))).thenReturn(listOf(CLIENT_DAO))
        `when`(springDataClientRepository.findByDocumentNumber(CLIENT.documentNumber)).thenReturn(CLIENT_DAO)
        `when`(springDataClientRepository.findAllByFirstOrLastName(listOf(RANDOM_FIRST_NAME, RANDOM_LAST_NAME))).thenReturn(emptyList())
        `when`(springDataClientRepository.findByDocumentNumber(RANDOM_DOCUMENT_NUMBER)).thenReturn(null)
        `when`(springDataClientRepository.findById(CLIENT_DAO.id)).thenReturn(Optional.of(CLIENT_DAO))
    }

    private fun givenClientRepository() {
        clientRepository = JpaClientRepository(springDataClientRepository)
    }

    private fun whenSavingTheClient() {
        clientSaved = clientRepository.save(CLIENT)
    }

    private fun whenFindingClientsByName(vararg names: String) {
        clientsFound = clientRepository.findByFirstNameOrLastNameIn(names.toList())
    }

    private fun whenFindingClientByDocumentNumber(documentNumber: String) {
        clientFound = clientRepository.findByDocument(documentNumber)
    }

    private fun whenFindingClientById(clientId: Long) {
        clientFound = clientRepository.findById(clientId)
    }

    private fun thenInputSaved() {
        verify(springDataClientRepository).save(Mockito.any())
        then(clientSaved).isNotNull
    }

    private fun thenClientsWhereFound() {
        then(clientsFound).contains(CLIENT)
    }

    private fun thenClientHasBeenFound() {
        then(clientFound).isEqualTo(CLIENT)
    }

    private fun thenClientHasBeenFoundById() {
        verify(springDataClientRepository).findById(CLIENT.id)
        then(clientFound).isNotNull
    }

    private fun thenClientHasNotFoundById() {
        verify(springDataClientRepository).findById(1L)
        then(clientFound).isNull()
    }

    private fun thenNoClientWhereFound() {
        then(clientFound).isNull()
    }

    private fun thenNoClientsWhereFound() {
        then(clientsFound).isEmpty()
    }

    private fun thenTheFoundClientIsReturned() {
        then(clientFound).isEqualTo(CLIENT)
    }

    private companion object {
        const val RANDOM_DOCUMENT_NUMBER = "40060441"
        const val RANDOM_FIRST_NAME = "Random"
        const val RANDOM_LAST_NAME = "Name"
        val CLIENT_DAO = ClientRepresentation(0L, "00000000", "First", "Last", "", 0.0, "", "", "")
        val CLIENT = Client(0L, "00000000", "First", "Last", "", 0.0, "", "", "")
    }
}