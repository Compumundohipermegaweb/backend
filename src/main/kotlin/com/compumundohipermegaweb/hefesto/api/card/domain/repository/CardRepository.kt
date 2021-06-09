package com.compumundohipermegaweb.hefesto.api.card.domain.repository

import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card

interface CardRepository {
    fun save(card: Card): Card
    fun findAllCards(): List<Card>
    fun findById(cardId: Long): Card?
}