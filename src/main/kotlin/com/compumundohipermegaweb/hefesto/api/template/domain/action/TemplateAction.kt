package com.compumundohipermegaweb.hefesto.api.template.domain.action

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.service.TemplateService

/**
 * Un Action representa una acción que puede realizar nuestro feature. La idea de orientar la arquitectura a acciones
 * es que cuando querés ver cuáles son las capacidades de un feature es tan simple como entrar a domain.action y ver
 * el listado de acciones que es posible de realizar.
 *
 * Un Action expone un ÚNICO método público, en nuestro caso al estar usando Kotlin vamos a exponer el
 * "operator fun invoke". Este operador tiene la particularidad que para llamarlo no es necesario escribirlo de manera
 * explicita. Por ej. para invocar vamos a suponer que fue definido como "private val templateAction: TemplateAction" en
 * donde fuese necesario inyectarlo, entonces para llamarlo deberiamos deberíamos escribir el nombre de la variable
 * con el que fue definido nuestro Action como si fuese un llamado de una funcion, en este caso sería: templateAction()
 *
 * El operator invoke no tiene ningún parametro ni tipo de retorno definido por defecto, no es un override lo que se hace,
 * por lo que podemos definir que parametros de entrada recibe y que devuelve (si tiene que devolver algo)
 */
class TemplateAction(private val templateService: TemplateService) {

    operator fun invoke(input: String): TemplateDomainObject {
        return when(input) {
            "casa" -> TemplateDomainObject("asac", "")
            else -> templateService.calculateResult(input)
        }
    }

}
