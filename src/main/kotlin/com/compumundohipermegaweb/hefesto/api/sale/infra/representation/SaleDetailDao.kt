package com.compumundohipermegaweb.hefesto.api.sale.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "SALE_DETAIL")
@Audited
data class SaleDetailDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                         @Column(name = "SKU") val sku: String,
                         @Column(name = "DESCRIPTION") val description: String,
                         @Column(name = "SALE_ID") val saleId: Long,
                         @Column(name = "QUANTITY") val quantity: Int,
                         @Column(name = "UNIT_PRICE") val unitPrice: Double)
