package com.compumundohipermegaweb.hefesto.api.config.audit.infra.representation

import org.hibernate.envers.Audited
import javax.persistence.*

@Entity
@Table(name = "AUDIT_REPRESENTATION")
@Audited
data class AuditRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
                               @Column(name = "NAME") val name: String)
