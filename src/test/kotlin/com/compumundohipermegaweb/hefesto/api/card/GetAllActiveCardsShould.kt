package com.compumundohipermegaweb.hefesto.api.card

import com.compumundohipermegaweb.hefesto.api.card.domain.action.GetActiveCards
import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card
import com.compumundohipermegaweb.hefesto.api.card.domain.repository.CardRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetAllActiveCardsShould {
    private lateinit var cardRepository: CardRepository
    private lateinit var getActiveCards: GetActiveCards

    private lateinit var cardFound: List<Card>

    @Test
    fun `find all active cards`() {
        givenCardRepository()
        givenGetActiveCardAction()

        whenFindingAllActiveCards()

        thenAllActiveCardsBeenFound()
    }

    @Test
    fun `return all found active cards`() {
        givenCardRepository()
        givenGetActiveCardAction()

        whenFindingAllActiveCards()

        thenActiveCardsFoundBeenReturned()
    }

    private fun givenCardRepository() {
        cardRepository = mock()
        `when`(cardRepository.findAllCards()).thenReturn(CARDS_REPRESENTATION)
    }

    private fun givenGetActiveCardAction() {
        getActiveCards = GetActiveCards(cardRepository)

    }

    private fun whenFindingAllActiveCards() {
        cardFound = getActiveCards.invoke()
    }

    private fun thenAllActiveCardsBeenFound() {
        verify(cardRepository).findAllCards()
        then(cardFound).isNotEmpty
        then(cardFound.size).isEqualTo(2)
    }

    private fun thenActiveCardsFoundBeenReturned() {
        then(cardFound).isEqualTo(EXPECTED_CARDS)
    }

    private companion object {

        val EXPECTED_CARDS = listOf(
            Card(0L, "VISA", "ACTIVE"),
            Card(1L, "AHORA12", "ACTIVE"),
        )

        val CARDS_REPRESENTATION = listOf(
            Card(0L, "VISA", "ACTIVE"),
            Card(1L, "AHORA12", "ACTIVE"),
            Card(2L, "MASTERCARD", "INACTIVE")
        )
    }
}