package com.compumundohipermegaweb.hefesto.api.config

import com.compumundohipermegaweb.hefesto.api.config.audit.domain.model.Audit
import com.compumundohipermegaweb.hefesto.api.config.audit.domain.repository.AuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository.JpaAuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository.SpringDataAuditDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource


@SpringBootTest
@TestPropertySource(locations = ["classpath:db-test.properties"])
class AuditConfigShould {
    @Autowired
    private lateinit var springDataAuditDao: SpringDataAuditDao
    private lateinit var auditRepository: AuditRepository

    private lateinit var auditSaved: Audit

    @Test
    fun `save the changes in audit tables`() {
        givenAuditRepository()

        whenCreatingAuditDaoEntity()

        thenTheCreationHasAudited()
    }

    private fun givenAuditRepository() {
        auditRepository = JpaAuditRepository(springDataAuditDao)
    }

    private fun whenCreatingAuditDaoEntity() {
        auditSaved = auditRepository.save(AUDIT)
    }

    private fun thenTheCreationHasAudited() {
        then(auditSaved).isNotNull
    }

    private companion object {
        private val AUDIT = Audit(0L, "AUDIT TEST")
    }
}