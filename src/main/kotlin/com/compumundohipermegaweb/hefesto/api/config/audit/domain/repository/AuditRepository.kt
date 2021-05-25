package com.compumundohipermegaweb.hefesto.api.config.audit.domain.repository

import com.compumundohipermegaweb.hefesto.api.config.audit.domain.model.Audit

interface AuditRepository {
    fun save(audit: Audit): Audit
}