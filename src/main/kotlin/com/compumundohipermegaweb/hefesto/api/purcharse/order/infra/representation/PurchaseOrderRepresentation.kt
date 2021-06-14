package com.compumundohipermegaweb.hefesto.api.purcharse.order.infra.representation

import javax.persistence.*

@Entity
@Table(name = "PURCHASE_ORDER")
data class PurchaseOrderRepresentation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "BRANCH_ID")
        val branchId: Long,

        @Column(name = "SKU")
        val sku: String,

        @Column(name = "AMOUNT")
        val amount: Int,

        @Column(name = "SUPPLIER")
        val supplier: String,

        @Column(name = "STATUS")
        val status: String,

        @Column(name = "DISPATCH_ID")
        val dispatchId: Long)


