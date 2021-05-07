package com.compumundohipermegaweb.hefesto.api.template.domain.service

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.repository.TemplateRepository

class DefaultTemplateService(private val templateRepository: TemplateRepository): TemplateService {

    override fun calculateResultado(input: String): TemplateDomainObject {
        return templateRepository.find(input)
    }
}
