package com.compumundohipermegaweb.hefesto.api.template.config

import com.compumundohipermegaweb.hefesto.api.template.domain.action.TemplateAction
import com.compumundohipermegaweb.hefesto.api.template.domain.repository.TemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.domain.service.DefaultTemplateService
import com.compumundohipermegaweb.hefesto.api.template.domain.service.TemplateService
import com.compumundohipermegaweb.hefesto.api.template.infra.repository.JpaTemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.infra.repository.SpringDataTemplateClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Configuracion de nuestro feature. En esta clase le especificamos a bean cómo deben instanciarse los diferentes
 * objetos que fuimos definiendo ya que de lo contrario va a intentar inicilizarlas el solito y no va a poder ya que
 * ninguno de nuestros objetos de dominio (actions, etc.) está contemplado dentro del contexto de Spring.
 *
 * Requisitos para crear una configuración
 * @Configuration Especifica que esta clase contiene definicion de beans para que Sping pueda inicializarlos en runtime
 */
@Configuration
class TemplateConfiguration {

    /**
     * Un bean es un objeto presente en el contexto de Spring para que este pueda inyectarlo cuando se lo requiere
     *
     * Requisitos para crear un Bean
     * @Bean solo eso
     *
     * Consideraciones
     *  Un Bean esta definido por su nombre de función + tipo de retorno. Esto quiere decir que cuando queremos inyectar
     * un bean en algun lado tenemos que asegurarnos que el nombre de la variable y tipo de la variable coincidan con el
     * nombre y tipo de retorno de la funcion que define el bean. Ej: Al bean de acá abajo se le inyecta otro bean
     * "templateService: TemplateService", esto quiere decir que debemos asegurarnos de que exista un bean del estilo
     * "fun templateService(*dependenciasDelBean*): TemplateService"
     *  A pesar de que en general vamos a tener que crear los beans de gran parte de nuestra clases hay algunas exepciones.
     *  Como ya mencione la causa de que tengamos que definir los beans es que nuestros objetos de dominio no estan
     *  contemplados dentro del contexto de Spring hay algunos que si, ejemplo de estos son los Controller (anotados por @RestController)
     *  y los repositorios que hereden un repo de Spring Data, SpringDataTemplateClient por ejemplo, ya que hereda CrudRepository
     */
    @Bean
    fun templateAction(templateService: TemplateService): TemplateAction {
        return TemplateAction(templateService)
    }

    @Bean
    fun templateService(templateRepository: TemplateRepository): TemplateService {
        return DefaultTemplateService(templateRepository)
    }

    @Bean
    fun templateRepository(templateCrudRepository: SpringDataTemplateClient): TemplateRepository {
        return JpaTemplateRepository(templateCrudRepository)
    }
}