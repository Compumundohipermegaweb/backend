package com.compumundohipermegaweb.hefesto.api.sale.infra.representation

import javax.persistence.*

@Entity
@Table(name = "SALE")
data class SaleDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                   @Column(name = "TYPE") val type: String,
                   @Column(name = "CLIENT_ID") val clientId: Long,
                   @Column(name = "SALESMAN_ID") val salesmanId: Long,
                   @Column(name = "BRANCH_ID") val branchId: Long,
                   @Column(name = "INVOICE_ID") val invoiceId: Long,
                   @Column(name = "TOTAL") val total: Double)