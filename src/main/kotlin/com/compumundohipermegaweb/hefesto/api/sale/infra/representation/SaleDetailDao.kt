package com.compumundohipermegaweb.hefesto.api.sale.infra.representation

import javax.persistence.*

@Entity
@Table(name = "SALE_DETAIL")
data class SaleDetailDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                         @Column(name = "DESCRIPTION") val description: String,
                         @Column(name = "SALE_ID") val saleId: Long,
                         @Column(name = "QUANTITY") val quantity: Int,
                         @Column(name = "UNIT_PRICE") val unitPrice: Double)
