package com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.representation

import javax.persistence.*

@Entity
@Table (name = "PAYMENT_METHOD")
data class PaymentMethodDao (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
    @Column(name = "PAYMENT_METHOD") val paymentMethod: String,
    @Column(name = "STATE") val state: String
)