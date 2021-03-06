package com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "REJECTION_ITEM_DETAIL")
@Audited
data class RejectedItemDetailRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                                            @Column(name = "ITEM_ID") val itemId: Long?,
                                            @Column(name = "SKU") val sku: String?,
                                            @Column(name = "DESCRIPTION") val description: String,
                                            @Column(name = "REJECTED_SALE_ID") val rejectedSaleId: Long,
                                            @Column(name = "QUANTITY") val quantity: Int,
                                            @Column(name = "UNIT_PRICE") val unitPrice: Double,
                                            @Column(name = "REASON") val reason: String)