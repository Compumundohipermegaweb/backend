package com.compumundohipermegaweb.hefesto.api.template

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.repository.TemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.domain.service.DefaultTemplateService
import com.compumundohipermegaweb.hefesto.api.template.domain.service.TemplateService
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DefaultTemplateServiceShould {

    private lateinit var resultado: TemplateDomainObject

    private lateinit var templateRepository: TemplateRepository
    private lateinit var templateService: TemplateService

    @Test
    fun `devolver el resultado esperado`() {
        givenTemplateRepository()
        givenTemplateService()

        whenCalculationgTheResult()

        thenResultIsTheExpectedOne()
    }

    private fun givenTemplateRepository() {
        templateRepository = mock(TemplateRepository::class.java)
        `when`(templateRepository.find(INPUT)).thenReturn(RESULTADO_ESPERADO)
    }

    private fun givenTemplateService() {
        templateService = DefaultTemplateService(templateRepository)
    }

    private fun whenCalculationgTheResult() {
        resultado = templateService.calculateResultado(INPUT)
    }

    private fun thenResultIsTheExpectedOne() {
        then(resultado).isEqualTo(RESULTADO_ESPERADO)
    }

    private companion object {
        const val INPUT = "Espero el resultado"
        val RESULTADO_ESPERADO = TemplateDomainObject("resultado")
    }
}