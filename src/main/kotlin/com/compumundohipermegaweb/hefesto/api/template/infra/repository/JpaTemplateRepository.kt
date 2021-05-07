package com.compumundohipermegaweb.hefesto.api.template.infra.repository

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.repository.TemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.infra.representation.TemplateDao

class JpaTemplateRepository(private val springDataTemplateClient: SpringDataTemplateClient): TemplateRepository {

    override fun find(input: String): TemplateDomainObject {
        val templateDao = springDataTemplateClient.findByInput(input)
        return templateDao?.toTemplateDomainObject() ?: DEFAULT
    }

    override fun save(input: String, output: String) {
        TODO("Not yet implemented")
    }

    private fun TemplateDao.toTemplateDomainObject() = TemplateDomainObject(output)

    private companion object {
        val DEFAULT = TemplateDomainObject("")
    }

}
