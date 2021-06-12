package com.compumundohipermegaweb.hefesto.api.supplier.infra.representation

import javax.persistence.*

@Entity
@Table(name = "SUPPLIED_ITEM")
data class SuppliedItemRepresentation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name ="ID")
        val id: Long,

        @Column(name ="SUPPLIER_ID")
        val supplierId: Long,

        @Column(name ="SKU")
        val sku: String)