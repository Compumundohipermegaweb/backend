package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.representation

import javax.persistence.*

@Entity
@Table(name = "DISPATCH")
data class DispatchRepresentation(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "SUPPLIER_ID")
        val supplierId: Long,

        @Column(name = "TOTAL_COST")
        val totalCost: Double,

        @Column(name = "STATUS")
        val status: String)
