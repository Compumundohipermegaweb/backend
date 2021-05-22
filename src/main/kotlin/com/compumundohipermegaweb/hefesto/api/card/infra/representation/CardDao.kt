package com.compumundohipermegaweb.hefesto.api.card.infra.representation

import javax.persistence.*

@Entity
@Table(name = "CARD")
data class CardDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                   @Column(name = "CARD_NAME") val name: String,
                   @Column(name = "STATE") val state: String)
