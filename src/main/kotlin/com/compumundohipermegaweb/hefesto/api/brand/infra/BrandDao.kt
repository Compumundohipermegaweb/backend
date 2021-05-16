package com.compumundohipermegaweb.hefesto.api.brand.infra

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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
