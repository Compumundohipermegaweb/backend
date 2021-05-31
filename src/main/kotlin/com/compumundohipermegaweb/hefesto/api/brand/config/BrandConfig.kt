package com.compumundohipermegaweb.hefesto.api.brand.config

import com.compumundohipermegaweb.hefesto.api.brand.domain.action.FindAllBrands
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.JpaBrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.BrandDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrandConfig {

    @Bean
    fun findAllBrands(jpaBrandRepository: JpaBrandRepository): FindAllBrands {
        return FindAllBrands(jpaBrandRepository)
    }

    @Bean
    fun jpaBrandRepository(brandDao: BrandDao): BrandRepository {
        return JpaBrandRepository(brandDao)
    }
}