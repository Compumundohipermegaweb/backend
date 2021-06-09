package com.compumundohipermegaweb.hefesto.api.sale.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "SALE_PAYMENT")
@Audited
data class SalePaymentDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                          @Column(name = "SALE_ID") val saleId: Long,
                          @Column(name = "PAYMENT_METHOD_ID") val paymentMethodId: Long,
                          @Column(name = "CARD_ID") val cardId: Long?,
                          @Column(name = "LAST_DIGITS") val lastDigits: String?,
                          @Column(name = "REFERENCE") val email: String?,
                          @Column(name = "SUB_TOTAL") val subTotal: Double)
