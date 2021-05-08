package com.compumundohipermegaweb.hefesto.api.invoice.infra.representation

import javax.persistence.*

@Entity
@Table(name = "INVOICE")
data class InvoiceDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                      @Column(name = "SALE_ID") val saleId: Long,
                      @Column(name = "TYPE") val type: String,
                      @Column(name = "BRANCH_ID") val branchId: Long,
                      @Column(name = "SUB_TOTAL") val subTotal: Double,
                      @Column(name = "IVA_SUB_TOTAL") val ivaSubTotal: Double,
                      @Column(name = "TOTAL") val total: Double)