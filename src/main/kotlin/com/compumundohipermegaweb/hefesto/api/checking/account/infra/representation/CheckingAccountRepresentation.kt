package com.compumundohipermegaweb.hefesto.api.checking.account.infra.representation

import javax.persistence.*

@Entity
@Table(name = "CHECKING_ACCOUNT")
data class CheckingAccountRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                                         @Column(name = "CLIENT_ID") val clientId: Long,
                                         @Column(name = "CREDIT_LIMIT") val creditLimit: Double,
                                         @Column(name = "BALANCE_DUE") val balanceDue: Double,
                                         @Column(name = "BALANCE") val balance: Double)
