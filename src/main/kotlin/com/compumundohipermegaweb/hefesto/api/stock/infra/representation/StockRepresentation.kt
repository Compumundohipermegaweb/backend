package com.compumundohipermegaweb.hefesto.api.stock.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "STOCK")
@Audited
data class StockRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                               @Column(name = "SKU") val sku: String,
                               @Column(name = "BRANCH_ID") val branchId: Long,
                               @Column(name = "STOCK_TOTAL") var stockTotal: Int,
                               @Column(name = "MINIMUM_STOCK") val minimumStock: Int,
                               @Column(name = "SECURITY_STOCK") val securityStock: Int)
