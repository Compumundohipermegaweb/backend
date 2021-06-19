package com.compumundohipermegaweb.hefesto.api.order.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "SALE_ORDER")
@Audited
data class OrderRepresentation(@Id
                               @GeneratedValue(strategy = GenerationType.IDENTITY)
                               @Column(name = "ID")
                               val id: Long,
                               @Column(name = "SALE_ID")
                               val saleId: Long,
                               @Column(name = "BRANCH_ID")
                               val branchId: Long,
                               @Column(name = "STATE")
                               val state: String,
                               @Column(name = "SHIPPING_PRICE")
                               val shippingPrice: Double,
                               @Column(name = "SHIPPING_COMPANY")
                               val shippingCompany: String)
