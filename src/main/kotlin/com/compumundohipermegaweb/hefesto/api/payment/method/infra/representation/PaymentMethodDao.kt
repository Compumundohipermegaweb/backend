package com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation

import javax.persistence.*

@Entity
@Table (name = "PAYMENT_METHOD")
data class PaymentMethodDao (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
    @Column(name = "DESCRIPTION") val description: String,
    @Column(name = "STATE") val state: String
)
