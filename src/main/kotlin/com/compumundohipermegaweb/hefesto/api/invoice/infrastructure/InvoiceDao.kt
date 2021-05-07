package com.compumundohipermegaweb.hefesto.api.invoice.infrastructure

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "INVOICE")
data class InvoiceDao(@Id @Column(name = "ID") val id: Long,
                      @Column(name = "TYPE") val type: String,
                      @Column(name = "ID_CLIENT") val idClient: Long,
                      @Column(name = "SALESMAN_ID") val idSalesman: Long,
                      @Column(name = "BRANCH_ID") val idBranch: Long,
                      @Column(name = "ITEMS") val items: String,
                      @Column(name = "TOTAL") val total: Double,
                      @Column(name = "SHORT_PAYMENTS_DETAILS") val shortPaymentsDetails: String)
