package com.compumundohipermegaweb.hefesto.api.discount.infra.repository

import javax.persistence.*

@Entity
@Table(name = "DISCOUNT")
data class DiscountRepresentation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "PERCENTAGE")
        val percentage: Int,

        @Column(name = "AMOUNT")
        val amount: Double,

        @Column(name = "SALE_ID")
        val saleId: Long)
