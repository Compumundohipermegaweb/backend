package com.compumundohipermegaweb.hefesto.api.template.domain.service

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject

/**
 * Las interfaces de los servicios no son requeridas a menos que la implementación del servicio sea ajena al dominio
 */
interface TemplateService {
    fun calculateResult(input: String): TemplateDomainObject
}
