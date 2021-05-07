package com.compumundohipermegaweb.hefesto.api.template.rest.controller

import com.compumundohipermegaweb.hefesto.api.template.domain.action.TemplateAction
import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.rest.representation.PostTemplateRequest
import com.compumundohipermegaweb.hefesto.api.template.rest.representation.GetTemplateResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Los Controlles son el punto de acceso a nuestra aplicación desde el exterior.
 *
 * Requisitos para crear un Controller
 * @RestController necesario para que Spring se entere de que tiene que dirigir a loos metodos definidos a esta clase cuando
 * le llega un request que concuerda con nuestro path (@RequestMapping) definido
 * @RequestMapping path base de nuestro recurso/controller, este se aplica a todos los Mappings que se definan dentro de la clase
 * @Mappings el mapping va a depende de la funcionalidad que se esté expòniendo @PostMapping para creación de objetos,
 * o ejecución de procesos, @GetMapping para la obtención de objetos, @PutMapping para la modificación de objetos y
 * por ultimo @DeleteMapping para eliminarlos.
 * Dentro de nuestras funciones podemos definir diferentes annotations en funcion de lo que requiera nuestro end point.
 * @PathVariable define una variable presente en la URI: http://localhost:8080/api/coleccion/PathVariable
 * @RequestParam define un parametro de la query de nuestra URL: http://localhost:8080/api/coleccion?RequestParam=value
 * @RequestBody define el body de un request
 */
@RestController
@RequestMapping("/api/templates")
class TemplateController(private val templateAction: TemplateAction) {

    /**
     * Ejemplo de GetMapping usando PathVariable para indicar el ID del elemento de la coleccion que queremos obtener
     * y un RequestParam solo para usarlo
     */
    @GetMapping("/{ID}")
    fun getInput(@PathVariable(name = "ID") id: Long,
                 @RequestParam(name = "input") input: String): ResponseEntity<GetTemplateResponse> {
        val templateDomainObject = templateAction(input)
        return ResponseEntity.ok(templateDomainObject.toTemplateGetResponse(id))
    }

    /**
     * Ejemplo de post mapping recibiendo el body
     */
    @PostMapping
    fun postInput(@RequestBody body: PostTemplateRequest): ResponseEntity<PostTemplateRequest> {
        return ResponseEntity.ok(body)
    }


    private fun TemplateDomainObject.toTemplateGetResponse(id: Long) = GetTemplateResponse(id, input)
}
