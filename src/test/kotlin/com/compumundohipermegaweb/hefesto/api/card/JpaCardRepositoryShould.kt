package com.compumundohipermegaweb.hefesto.api.card

import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card
import com.compumundohipermegaweb.hefesto.api.card.domain.repository.CardRepository
import com.compumundohipermegaweb.hefesto.api.card.infra.repository.JpaCardRepository
import com.compumundohipermegaweb.hefesto.api.card.infra.repository.SpringDataCardDao
import com.compumundohipermegaweb.hefesto.api.card.infra.representation.CardRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaCardRepositoryShould {
    private lateinit var springDataCardDao: SpringDataCardDao
    private lateinit var cardRepository: CardRepository

    private lateinit var cards: List<Card>

    @Test
    fun `find all cards`() {
        givenSpringDataCardRepository()
        givenCardRepository()

        whenFindingAllCards()

        thenAllCardsBeenFound()
    }

    @Test
    fun `return all found cards`() {
        givenSpringDataCardRepository()
        givenCardRepository()

        whenFindingAllCards()

        thenCardsFoundBeenReturned()
    }

    private fun givenSpringDataCardRepository() {
       springDataCardDao = mock()
       `when`(springDataCardDao.findAll()).thenReturn(CARDS_REPRESENTATION)
    }

    private fun givenCardRepository() {
        cardRepository = JpaCardRepository(springDataCardDao)
    }

    private fun whenFindingAllCards() {
        cards = cardRepository.findAllCards()
    }

    private fun thenAllCardsBeenFound() {
        verify(springDataCardDao).findAll()
        then(cards).isNotEmpty
        then(cards.size).isEqualTo(2)
    }

    private fun thenCardsFoundBeenReturned() {
        then(cards).isEqualTo(EXPECTED_CARDS)
    }

    private companion object {

        val EXPECTED_CARDS = listOf(
            Card(0L, "VISA", "ACTIVE"),
            Card(1L, "MASTERCARD", "INACTIVE"))

        val CARDS_REPRESENTATION = listOf(
            CardRepresentation(0L, "VISA", "ACTIVE"),
            CardRepresentation(1L, "MASTERCARD", "INACTIVE")
        )
    }
}