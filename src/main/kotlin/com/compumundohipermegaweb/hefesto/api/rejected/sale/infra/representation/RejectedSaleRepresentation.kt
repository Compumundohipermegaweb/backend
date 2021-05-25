package com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "REJECTED_SALE")
@Audited
data class RejectedSaleRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                                      @Column(name = "SALE_ID") val saleId: Long?,
                                      @Column(name = "TOTAL") val total: Double,
                                      @Column(name = "CATEGORY") val category: String,
                                      @Column(name = "REASON") val reason: String,
                                      @Column(name = "LEVEL") val level: String,)
