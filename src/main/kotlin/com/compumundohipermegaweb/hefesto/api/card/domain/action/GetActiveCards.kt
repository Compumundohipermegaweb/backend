package com.compumundohipermegaweb.hefesto.api.card.domain.action

import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card
import com.compumundohipermegaweb.hefesto.api.card.domain.repository.CardRepository
import kotlin.streams.toList

class GetActiveCards(private val cardRepository: CardRepository) {
    operator fun invoke(): List<Card> {
        return cardRepository.findAllCards().stream().filter { it.state == "ACTIVE" }.toList()
    }
}