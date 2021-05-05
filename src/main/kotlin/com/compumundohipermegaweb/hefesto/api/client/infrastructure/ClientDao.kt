package com.compumundohipermegaweb.hefesto.api.client.infrastructure

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ClientDao(@Id @Column(name = "ID") val id: Long,
                     @Column(name = "DOCUMENT_NUMBER") val documentNumber: String,
                     @Column(name = "FIST_NAME") val firstName: String,
                     @Column(name = "LAST_NAME") val lastName: String,
                     @Column(name = "SUR_NAME") val surName: String,
                     @Column(name = "CATEGORY") var category: String,
                     @Column(name = "EMAIL") var email: String,
                     @Column(name = "CONTACT_NUMBER") var contactNumber: String,
                     @Column(name = "CREATE_DATE") val createDate: LocalDateTime,
                     @Column(name = "CREATE_USER") val createUser: String,
                     @Column(name = "UPDATE_DATE") var updateDate: LocalDateTime,
                     @Column(name = "UPDATE_USER") var updateUser: String)
