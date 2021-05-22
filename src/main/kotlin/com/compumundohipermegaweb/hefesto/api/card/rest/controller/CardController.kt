package com.compumundohipermegaweb.hefesto.api.card.rest.controller

import com.compumundohipermegaweb.hefesto.api.card.domain.action.GetActiveCards
import com.compumundohipermegaweb.hefesto.api.card.domain.model.Card
import com.compumundohipermegaweb.hefesto.api.card.rest.response.CardResponse
import com.compumundohipermegaweb.hefesto.api.card.rest.response.CardsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cards")
class CardController(private val getActiveCards: GetActiveCards) {

    @GetMapping
    @RequestMapping("/active")
    fun getCards(): ResponseEntity<CardsResponse> {
        val cards = getActiveCards.invoke().map { it.toCardResponse() }
        return ResponseEntity.ok(CardsResponse(cards))
    }

    private fun Card.toCardResponse(): CardResponse {
        return CardResponse(id, name, state)
    }
}




