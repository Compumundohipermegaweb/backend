package com.compumundohipermegaweb.hefesto.api.category.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
@Audited
data class CategoryRepresentation(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "NAME")
        val name: String,

        @Column(name = "DESCRIPTION")
        val description: String,

        @Column(name = "DELETED")
        val deleted: Boolean)

