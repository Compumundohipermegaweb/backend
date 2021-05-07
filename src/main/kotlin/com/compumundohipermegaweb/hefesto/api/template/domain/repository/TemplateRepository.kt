package com.compumundohipermegaweb.hefesto.api.template.domain.repository

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject

/**
 * Un Repositorio define una colección de un objeto de dominio (definidos dentro de "model")
 * Un Repositorio definido dentro de "domain" implica un contrato/interfaz/protocolo que va a ser satisfecho por nuestra
 * infraestructura
 * Es importante abstraerse de la implementación que pueda tener nuestro repositorio en la infraestructura
 * para evitar aclopamiento, esto implica utilizar SIEMPRE la interfaz definida en el dominio
 */
interface TemplateRepository {

    fun find(input: String): TemplateDomainObject
    fun save(templateDomainObject: TemplateDomainObject)
}
