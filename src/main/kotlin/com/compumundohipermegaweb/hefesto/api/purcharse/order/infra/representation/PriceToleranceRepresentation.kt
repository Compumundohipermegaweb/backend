package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation

import javax.persistence.*

@Entity
@Table(name = "PRICE_TOLERANCE")
data class PriceToleranceRepresentation(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "TOLERANCE")
        val tolerance: Int)
