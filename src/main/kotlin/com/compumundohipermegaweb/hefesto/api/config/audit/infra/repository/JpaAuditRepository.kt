package com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository

import com.compumundohipermegaweb.hefesto.api.config.audit.domain.model.Audit
import com.compumundohipermegaweb.hefesto.api.config.audit.domain.repository.AuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.representation.AuditRepresentation

class JpaAuditRepository(private val springDataAuditDao: SpringDataAuditDao): AuditRepository {
    override fun save(audit: Audit): Audit {
        return springDataAuditDao.save(audit.toDao()).toAudit()
    }

    private fun Audit.toDao(): AuditRepresentation {
        return AuditRepresentation(id, name)
    }

    private fun AuditRepresentation.toAudit(): Audit {
        return Audit(id, name)
    }
}




