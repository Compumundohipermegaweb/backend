package com.compumundohipermegaweb.hefesto.api.card.infra.representation

import javax.persistence.*

@Entity
@Table(name = "CARD")
data class CardRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                              @Column(name = "NAME") val name: String,
                              @Column(name = "STATE") val state: String)
