package com.compumundohipermegaweb.hefesto.api.template.infra.repository

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.repository.TemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.infra.representation.TemplateDao

/**
 * Literal de un repositorio. Las implementaciones de nuestro repositorios van definidas en la infraestructura.
 * La principal función que tienen es la de mapear nuestros objetos de dominio a representaciones de la BBDD
 * En particular nuestro repositorios van a tener una dependencia denominada "SpringData_NOMBRE_DEL_MODELO_Client".
 * Ej: si nuestro modelo se llama Invoice, nuestra dependencia ser algo tipo SpringDataInvoiceClient
 */
class JpaTemplateRepository(private val springDataTemplateClient: SpringDataTemplateClient): TemplateRepository {

    override fun find(input: String): TemplateDomainObject {
        val templateDao = springDataTemplateClient.findByInput(input)
        return templateDao?.toTemplateDomainObject() ?: DEFAULT
    }

    override fun save(templateDomainObject: TemplateDomainObject) {
        val templateDao = TemplateDao(0L, templateDomainObject.input, templateDomainObject.output)
        springDataTemplateClient.save(templateDao)
    }

    private fun TemplateDao.toTemplateDomainObject() = TemplateDomainObject(output, output)

    private companion object {
        val DEFAULT = TemplateDomainObject("", "")
    }

}
