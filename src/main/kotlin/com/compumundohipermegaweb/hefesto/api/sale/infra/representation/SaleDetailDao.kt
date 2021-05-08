package com.compumundohipermegaweb.hefesto.api.sale.infra.representation

import javax.persistence.*

@Entity
@Table(name = "SALE_DETAILS_DAO")
data class SaleDetailDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                         @Column(name = "SALE_ID") val saleId: Long,
                         @Column(name = "QUANTITY") val quantity: Int,
                         @Column(name = "UNIT_PRICE") val unitPrice: Double)
