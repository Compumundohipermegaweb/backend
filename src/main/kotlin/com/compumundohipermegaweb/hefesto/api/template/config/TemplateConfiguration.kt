package com.compumundohipermegaweb.hefesto.api.template.config

import com.compumundohipermegaweb.hefesto.api.template.domain.action.TemplateAction
import com.compumundohipermegaweb.hefesto.api.template.domain.repository.TemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.domain.service.DefaultTemplateService
import com.compumundohipermegaweb.hefesto.api.template.domain.service.TemplateService
import com.compumundohipermegaweb.hefesto.api.template.infra.repository.JpaTemplateRepository
import com.compumundohipermegaweb.hefesto.api.template.infra.repository.SpringDataTemplateClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TemplateConfiguration {

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