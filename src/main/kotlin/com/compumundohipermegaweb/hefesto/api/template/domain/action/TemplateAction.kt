package com.compumundohipermegaweb.hefesto.api.template.domain.action

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.service.TemplateService

class TemplateAction(private val templateService: TemplateService) {

    operator fun invoke(input: String): TemplateDomainObject {
        return when(input) {
            "casa" -> TemplateDomainObject("asac")
            else -> templateService.calculateResultado(input)
        }
    }

}
