package com.compumundohipermegaweb.hefesto.api.config.audit.config

import com.compumundohipermegaweb.hefesto.api.config.audit.domain.repository.AuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository.JpaAuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository.SpringDataAuditDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuditConfig {

    @Bean
    fun auditRepository(springDataAuditDao: SpringDataAuditDao): AuditRepository {
        return JpaAuditRepository(springDataAuditDao)
    }
}