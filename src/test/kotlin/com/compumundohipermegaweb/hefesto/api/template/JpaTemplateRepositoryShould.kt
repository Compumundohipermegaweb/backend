package com.compumundohipermegaweb.hefesto.api.template

import com.compumundohipermegaweb.hefesto.api.template.domain.model.TemplateDomainObject
import com.compumundohipermegaweb.hefesto.api.template.infra.repository.JpaTemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.infra.repository.SpringDataTemplateClient
import com.compumundohipermegaweb.hefesto.api.template.infra.representation.TemplateDao
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class JpaTemplateRepositoryShould {

    private lateinit var springDataTemplateClient: SpringDataTemplateClient
    private lateinit var found: TemplateDomainObject
    private lateinit var templateRepository: JpaTemplateRepository

    @Test
    fun `find the result for a saved input`() {
        givenTemplateCrudRepository()
        givenTemplateRepository()

        whenFindingTheOutput()

        thenOutputFound()
    }

    @Test
    fun `save the input`() {
        givenTemplateCrudRepository()
        givenTemplateRepository()

        whenSavingTheInput()

        thenInputSaved()
    }

    private fun givenTemplateCrudRepository() {
        springDataTemplateClient = mock(SpringDataTemplateClient::class.java)
        `when`(springDataTemplateClient.findByInput("Input")).thenReturn(RESULT_DAO)
    }

    private fun givenTemplateRepository() {
        templateRepository = JpaTemplateRepository(springDataTemplateClient)
    }

    private fun whenFindingTheOutput() {
        found = templateRepository.find("Input")
    }

    private fun whenSavingTheInput() {
        templateRepository.save((TemplateDomainObject(INPUT, OUTPUT)))
    }

    private fun thenOutputFound() {
        then(found).isNotNull
    }

    private fun thenInputSaved() {
        verify(springDataTemplateClient).save(any())
    }

    private companion object {
        const val INPUT = "input"
        const val OUTPUT = "out"
        val RESULT_DAO = TemplateDao(1L, "Input", "Result")
    }
}