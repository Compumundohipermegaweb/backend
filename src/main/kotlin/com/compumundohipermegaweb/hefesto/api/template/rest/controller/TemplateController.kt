package com.compumundohipermegaweb.hefesto.api.template.rest.controller

import com.compumundohipermegaweb.hefesto.api.template.domain.action.TemplateAction
import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.rest.representation.TemplateGetResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/templates")
class TemplateController(private val templateAction: TemplateAction) {

    @GetMapping("/{ID}")
    fun getInput(@PathVariable(name = "ID") id: Long,
                 @RequestParam(name = "input") input: String): ResponseEntity<TemplateGetResponse> {
        val templateDomainObject = templateAction(input)
        return ResponseEntity.ok(templateDomainObject.toTemplateGetResponse(id))
    }


    private fun TemplateDomainObject.toTemplateGetResponse(id: Long) = TemplateGetResponse(id, sarasa)
}
