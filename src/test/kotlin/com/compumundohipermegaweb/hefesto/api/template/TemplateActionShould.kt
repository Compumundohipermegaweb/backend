package com.compumundohipermegaweb.hefesto.api.template

import com.compumundohipermegaweb.hefesto.api.template.domain.action.TemplateAction
import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.domain.service.TemplateService
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TemplateActionShould {

    private lateinit var templateService: TemplateService
    private lateinit var resultadoDelInvoke: TemplateDomainObject
    private lateinit var templateAction: TemplateAction

    @Test
    fun `devolver el resultado esperado cuando espera el resultado`() {
        givenTemplateService()
        givenTemplateAction()

        whenInvokeTheAction(INPUT)

        thenTheResultIsTheExpected(RESULTADO_ESEPRADO)
    }

    @Test
    fun `devolver asac para casa`() {
        givenTemplateService()
        givenTemplateAction()

        whenInvokeTheAction(INPUT_2)

        thenTheResultIsTheExpected(RESULTADO_AL_REVES)
    }

    @Test
    fun `devolver vacio para inputs desconocidos`() {
        givenTemplateService()
        givenTemplateAction()

        whenInvokeTheAction(INPUT_DESCONOCIDO)

        thenTheResultIsTheExpected(VACIO)
    }


    private fun givenTemplateService() {
        templateService = mock(TemplateService::class.java)
        `when`(templateService.calculateResult(INPUT)).thenReturn(RESULTADO_ESEPRADO)
        `when`(templateService.calculateResult(INPUT_DESCONOCIDO)).thenReturn(VACIO)
    }

    private fun givenTemplateAction() {
        templateAction = TemplateAction(templateService)
    }

    private fun whenInvokeTheAction(input: String) {
        resultadoDelInvoke = templateAction(input)
    }

    private fun thenTheResultIsTheExpected(expected: TemplateDomainObject) {
        then(resultadoDelInvoke).isEqualTo(expected)
    }

    private companion object {
        const val INPUT = "Espero el resultado"
        const val INPUT_2 = "casa"
        const val INPUT_DESCONOCIDO = "Input desconocido"
        val RESULTADO_ESEPRADO = TemplateDomainObject("El resultado", "")
        val RESULTADO_AL_REVES = TemplateDomainObject("asac","")
        val VACIO = TemplateDomainObject("", "")
    }
}