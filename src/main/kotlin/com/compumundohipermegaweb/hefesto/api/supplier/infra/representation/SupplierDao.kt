package com.compumundohipermegaweb.hefesto.api.supplier.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name ="SUPPLIER")
@Audited
data class SupplierDao (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
    @Column(name = "ORGANIZATION") val organization: String,
    @Column(name = "CONTACT_NAME") val contactName: String,
    @Column(name = "CONTACT_NUMBER") val contactNumber: String,
    @Column(name = "EMAIL")  val email: String,
    @Column(name = "CUIT") val cuit: String
 )