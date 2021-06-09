package com.compumundohipermegaweb.hefesto.api.card.infra.repository

import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card
import com.compumundohipermegaweb.hefesto.api.card.domain.repository.CardRepository
import com.compumundohipermegaweb.hefesto.api.card.infra.representation.CardRepresentation
import com.compumundohipermegaweb.hefesto.api.cash.domain.model.Cash

class JpaCardRepository(private val springDataCardDao: SpringDataCardDao): CardRepository {
    override fun save(card: Card): Card {
        return springDataCardDao.save(card.toCardRepresentation()).toCard()
    }

    override fun findAllCards(): List<Card> {
        return springDataCardDao.findAll().map { it.toCard() }
    }

    private fun Card.toCardRepresentation(): CardRepresentation {
        return CardRepresentation(id, name, state)
    }

    private fun CardRepresentation.toCard(): Card {
        return Card(id, name, state)
    }

    override fun findById(cardId: Long): Card? {
        val cardRepresentation = springDataCardDao.findById(cardId)
        if(cardRepresentation.isPresent) {
            return cardRepresentation.get().toCard()
        }
        return null
    }
}




