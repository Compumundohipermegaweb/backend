package com.compumundohipermegaweb.hefesto.api.template.domain.service

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject

interface TemplateService {

    fun calculateResultado(input: String): TemplateDomainObject

}
