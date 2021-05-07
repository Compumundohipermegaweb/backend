package com.compumundohipermegaweb.hefesto.api.template.infra.representation

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TEMPLATE")
data class TemplateDao(
        @Id @Column(name ="ID") val id: Long,
        @Column(name = "INPUT") val input: String,
        @Column(name = "OUTPUT") val output: String
)
