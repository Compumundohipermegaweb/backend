package com.compumundohipermegaweb.hefesto.api.cash.infra.representation

import org.hibernate.envers.Audited
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "CASH_MOVEMENT")
@Audited
data class CashMovementRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                                      @Column(name = "ID")
                                      val id: Long,
                                      @Column(name = "CASH_START_END_ID")
                                      val cashStartEndId: Long,
                                      @Column(name = "MOVEMENT_ID")
                                      val movementType: String,
                                      @Column(name = "DATE_TIME")
                                      val dateTime: Date,
                                      @Column(name = "SOURCE_ID")
                                      val sourceId: Long,
                                      @Column(name = "SOURCE_DESCRIPTION")
                                      val sourceDescription: String,
                                      @Column(name = "PAYMENT_METHOD_ID")
                                      val paymentMethodId: Long,
                                      @Column(name = "TRANSACTION_ID")
                                      val transactionId: Long,
                                      @Column(name = "USER_ID")
                                      val userId: Long,
                                      @Column(name = "AMOUNT")
                                      val amount: Double,
                                      @Column(name = "DETAIL")
                                      val detail: String)
