package com.compumundohipermegaweb.hefesto.api.stock.infra.representation

import javax.persistence.*

@Entity
@Table(name = "STOCK")
data class StockDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                    @Column(name = "SKU") val sku: Long,
                    @Column(name = "STOCK_TOTAL") val stockTotal: Int,
                    @Column(name = "MINIMUM_STOCK") val minimumStock: Int,
                    @Column(name = "SECURITY_STOCK") val securityStock: Int,
                    @Column(name = "SUPPLIER") val supplier: String,
                    @Column(name = "CURRENCY") val currency: String,
                    @Column(name = "COST") val cost: Double)
