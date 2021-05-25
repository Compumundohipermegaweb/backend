package com.compumundohipermegaweb.hefesto.api.config.audit.rest.controller

import com.compumundohipermegaweb.hefesto.api.config.audit.domain.model.Audit
import com.compumundohipermegaweb.hefesto.api.config.audit.domain.repository.AuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.rest.response.AuditResponse
import com.compumundohipermegaweb.hefesto.api.config.audit.rest.resquest.AuditRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/audit")
class AuditController(private val auditRepository: AuditRepository) {

    @PostMapping
    fun saveAudit(@RequestBody auditRequest: AuditRequest): ResponseEntity<AuditResponse> {
        return ResponseEntity.ok(auditRepository.save(auditRequest.toAudit()).toAuditResponse())
    }

    private fun AuditRequest.toAudit(): Audit {
        return Audit(id, name)
    }

    private fun Audit.toAuditResponse(): AuditResponse {
        return AuditResponse(id, name)
    }
}




