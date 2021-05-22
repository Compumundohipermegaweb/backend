package com.compumundohipermegaweb.hefesto.api.card.domain.service

import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card

interface CardService {
    fun saveCard(card: Card): Card
    fun getAllCards(): List<Card>
}