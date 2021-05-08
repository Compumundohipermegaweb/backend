package com.compumundohipermegaweb.hefesto.api.item.infrastructure

import javax.persistence.*

@Entity
@Table(name = "ITEM")
data class ItemDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID")val id: Long,
                   @Column(name = "SKU") val sku: String,
                   @Column(name = "SHORT_DESCRIPTION") val shortDescription: String,
                   @Column(name = "LONG_DESCRIPTION") val longDescription: String,
                   @Column(name = "MEASURE") val measure: String,
                   @Column(name = "STOCK_TOTAL") val stockTotal: Int,
                   @Column(name = "MINIMUM_STOCK") val minimumStock: Int,
                   @Column(name = "SECURITY_STOCK") val securityStock: Int,
                   @Column(name = "SUPPLIER")val supplier: String,
                   @Column(name = "COST") val cost: Double,
                   @Column(name = "UNIT_PRICE") val unitPrice: Double)