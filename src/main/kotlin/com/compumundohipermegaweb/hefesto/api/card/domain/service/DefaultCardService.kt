package com.compumundohipermegaweb.hefesto.api.card.domain.service

import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card
import com.compumundohipermegaweb.hefesto.api.card.domain.repository.CardRepository

class DefaultCardService(private val cardRepository: CardRepository): CardService {
    override fun saveCard(card: Card): Card {
        return cardRepository.save(card)
    }

    override fun getAllCards(): List<Card> {
        return cardRepository.findAllCards()
    }
}