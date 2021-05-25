package com.compumundohipermegaweb.hefesto.api.sale.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "SALE_PAYMENT")
@Audited
data class SalePaymentDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                          @Column(name = "SALE_ID") val saleId: Long,
                          @Column(name = "TYPE") val type: String,
                          @Column(name = "SUB_TOTAL") val subTotal: Double)
