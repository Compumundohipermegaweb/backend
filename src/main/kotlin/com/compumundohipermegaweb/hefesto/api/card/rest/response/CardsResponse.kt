package com.compumundohipermegaweb.hefesto.api.card.rest.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CardsResponse(@JsonProperty("cards") val cards: List<CardResponse>)
