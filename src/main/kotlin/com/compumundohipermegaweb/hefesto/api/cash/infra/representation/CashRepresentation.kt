package com.compumundohipermegaweb.hefesto.api.cash.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "CASH")
@Audited
data class CashRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                              @Column(name = "ID")
                              val id: Long,
                              @Column(name = "BRANCH_ID")
                              val branchId: Long,
                              @Column(name = "POINT_OF_SALE")
                              val pointOfSale: Long,
                              @Column(name = "STATUS")
                              val status: String)
