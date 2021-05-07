package com.compumundohipermegaweb.hefesto.api.template.domain.service

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.repository.TemplateRepository

/**
 * Los servicios son una capa de abstarcción que proveen un set de funcionalidades único sobre un objeto de dominio
 * No es necesario definir una interfaz por cada servicio que se crea, en la mayorias de los casos no se va a crear, solo
 * ES necesario si la implementación de nuestro servicio es externa a nuestro dominio, por ejemplo, necesitamos crear un
 * servicio para consumir una REST API externa, al igual que con los repositorios la interfaz del Servicio va a formar
 * parte de nuestro dominio pero su implementación no, va a estar en la infraestructura
 * A diferencia de los Action los Servicios no necesariamente exponen un único método público, puede tener tantos como
 * sea necesario siempre y cuando no se lo sobrecargue de responsabilidades
 */
class DefaultTemplateService(private val templateRepository: TemplateRepository): TemplateService {

    override fun calculateResult(input: String): TemplateDomainObject {
        return templateRepository.find(input)
    }
}
