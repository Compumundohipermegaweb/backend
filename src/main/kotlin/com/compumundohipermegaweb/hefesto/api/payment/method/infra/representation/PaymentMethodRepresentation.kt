package com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table (name = "PAYMENT_METHOD")
@Audited
data class PaymentMethodRepresentation (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
    @Column(name = "TYPE") val type: String,
    @Column(name = "DESCRIPTION") val description: String,
    @Column(name = "STATE") val state: String
)
