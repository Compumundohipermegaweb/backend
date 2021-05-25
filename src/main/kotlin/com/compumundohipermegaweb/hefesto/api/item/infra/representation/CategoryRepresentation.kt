package com.compumundohipermegaweb.hefesto.api.item.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
@Audited
data class CategoryRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID")val id: Long,
                                  @Column(name = "NAME") val name: String)

