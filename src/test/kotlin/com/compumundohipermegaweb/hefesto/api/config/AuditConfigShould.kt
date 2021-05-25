package com.compumundohipermegaweb.hefesto.api.config

import com.compumundohipermegaweb.hefesto.api.config.audit.domain.model.Audit
import com.compumundohipermegaweb.hefesto.api.config.audit.domain.repository.AuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository.JpaAuditRepository
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.repository.SpringDataAuditDao
import com.compumundohipermegaweb.hefesto.api.config.audit.infra.representation.AuditRepresentation
import org.assertj.core.api.BDDAssertions.then
import org.hibernate.envers.AuditReaderFactory
import org.hibernate.envers.query.AuditEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.transaction.TestTransaction
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional


@SpringBootTest
@TestPropertySource(locations = ["classpath:db-test.properties"])
@Transactional
class AuditConfigShould {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

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
        TestTransaction.flagForCommit()
        auditRepository = JpaAuditRepository(springDataAuditDao)
        TestTransaction.end()
    }

    private fun whenCreatingAuditDaoEntity() {
        auditSaved = auditRepository.save(AUDIT)
    }

    private fun thenTheCreationHasAudited() {
        TestTransaction.start()
        val reader = AuditReaderFactory.get(entityManager)
        val query = reader.createQuery().forRevisionsOfEntity(AuditRepresentation::class.java, false, true)
        then(auditSaved).isNotNull
        then(query).isNotNull
        then(query.resultList.size).isEqualTo(1)
        TestTransaction.end()
    }

    private companion object {
        private val AUDIT = Audit(0L, "AUDIT TEST")
    }
}