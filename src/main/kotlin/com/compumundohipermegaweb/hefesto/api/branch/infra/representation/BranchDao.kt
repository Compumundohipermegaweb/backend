package com.compumundohipermegaweb.hefesto.api.branch.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name ="BRANCH")
@Audited
data class BranchDao (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  @Column (name = "ID")  val id : Long,
    @Column (name = "BRANCH") val branch : String,
    @Column (name = "ADDRESS") val address : String,
    @Column (name = "POSTAL_CODE") val postalCode : String,
    @Column (name = "EMAIL") val email : String,
    @Column (name = "CONTACT_NUMBER") val contactNumber : String,
    @Column (name = "ATTENTION_SCHEDULE") val attentionSchedule : String
)
