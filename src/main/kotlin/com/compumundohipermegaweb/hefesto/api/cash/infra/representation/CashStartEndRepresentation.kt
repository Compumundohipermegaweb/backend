package com.compumundohipermegaweb.hefesto.api.cash.infra.representation

import org.hibernate.envers.Audited
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CASH_START_END")
@Audited
data class CashStartEndRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                                      @Column(name = "ID")
                                      val id: Long,
                                      @Column(name = "CASH_ID")
                                      val cashId: Long,
                                      @Column(name = "OPEN_DATE")
                                      val openDate: Date,
                                      @Column(name = "OPENING_BALANCE")
                                      val openingBalance: Double,
                                      @Column(name = "USER_ID")
                                      val userId: Long,
                                      @Column(name = "CLOSE_DATE")
                                      val closeDate: Date,
                                      @Column(name = "REAL_BALANCE")
                                      val realBalance: Double,
                                      @Column(name = "THEORETICAL_BALANCE")
                                      val theoreticalBalance: Double,
                                      @Column(name = "DATE")
                                      val date: Date
)
