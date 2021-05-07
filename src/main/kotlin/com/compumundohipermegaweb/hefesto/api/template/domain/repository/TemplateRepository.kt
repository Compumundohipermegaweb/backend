package com.compumundohipermegaweb.hefesto.api.template.domain.repository

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject

interface TemplateRepository {

    fun find(input: String): TemplateDomainObject
    fun save(input: String, output: String)
}
