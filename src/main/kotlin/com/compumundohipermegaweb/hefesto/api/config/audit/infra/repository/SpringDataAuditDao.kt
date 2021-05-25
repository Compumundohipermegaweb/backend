package com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository

import com.compumundohipermegaweb.hefesto.api.config.audit.infra.representation.AuditRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringDataAuditDao: CrudRepository<AuditRepresentation, Long>