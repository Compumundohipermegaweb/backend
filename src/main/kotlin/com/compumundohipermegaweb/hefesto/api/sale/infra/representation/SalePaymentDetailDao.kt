package com.compumundohipermegaweb.hefesto.api.sale.infra.representation

import javax.persistence.*

@Entity
@Table(name = "SALE_ITEM_DETAIL")
data class SalePaymentDetailDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                                @Column(name = "SALE_ID") val saleId: Long,
                                @Column(name = "TYPE") val type: String,
                                @Column(name = "SUB_TOTAL") val subTotal: Double)
