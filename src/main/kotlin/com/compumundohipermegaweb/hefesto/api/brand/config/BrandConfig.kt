package com.compumundohipermegaweb.hefesto.api.brand.config

import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.JpaBrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.SpringDataBrandClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrandConfig {

    @Bean
    fun jpaBrandRepository(springDataBrandClient: SpringDataBrandClient): BrandRepository {
        return JpaBrandRepository(springDataBrandClient)
    }
}