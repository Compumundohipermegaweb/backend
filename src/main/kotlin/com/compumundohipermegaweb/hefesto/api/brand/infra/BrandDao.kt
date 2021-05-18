package com.compumundohipermegaweb.hefesto.api.brand.infra

import javax.persistence.*

@Entity
@Table(name ="BRAND")
class BrandDao(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "NAME", nullable = false)
        val name: String
)
