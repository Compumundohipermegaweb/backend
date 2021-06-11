package com.compumundohipermegaweb.hefesto.api.authentication.infra.representation

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "APP_USER")
data class UserRepresentation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    val id: Long,

    @Column(name = "CODE")
    val code: String,

    @Column(name = "USERNAME")
    val username: String,

    @Column(name = "PASSWORD")
    val password: String,

    @Column(name = "ROLE")
    val role: String)
