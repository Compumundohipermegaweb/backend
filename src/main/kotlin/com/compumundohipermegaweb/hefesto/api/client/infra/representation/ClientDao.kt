package com.compumundohipermegaweb.hefesto.api.client.infra.representation

import javax.persistence.*

@Entity
@Table(name = "CLIENT")
data class ClientDao(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                     @Column(name = "DOCUMENT_NUMBER") val documentNumber: String,
                     @Column(name = "FIST_NAME") val firstName: String,
                     @Column(name = "LAST_NAME") val lastName: String,
                     @Column(name = "STATE") val state: String,
                     @Column(name = "CREDIT_LIMIT")  val creditLimit: Double,
                     @Column(name = "EMAIL") val email: String,
                     @Column(name = "CONTACT_NUMBER") val contactNumber: String,
                     @Column(name = "ADDRESS") val address: String?)
