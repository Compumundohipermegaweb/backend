package com.compumundohipermegaweb.hefesto.api.template.infra.repository

import com.compumundohipermegaweb.hefesto.api.template.infra.representation.TemplateDao
import org.springframework.data.repository.CrudRepository

interface SpringDataTemplateClient: CrudRepository<TemplateDao, Long> {

    fun findByInput(input: String): TemplateDao?
}
