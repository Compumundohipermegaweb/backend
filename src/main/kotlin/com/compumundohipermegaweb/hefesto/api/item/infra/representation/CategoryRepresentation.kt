package com.compumundohipermegaweb.hefesto.api.item.infra.representation

import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
data class CategoryRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID")val id: Long,
                                  @Column(name = "NAME") val name: String)

