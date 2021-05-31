package com.compumundohipermegaweb.hefesto.api.brand.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name ="BRAND")
@Audited
class BrandRepresentation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "NAME", nullable = false)
        val name: String
)
