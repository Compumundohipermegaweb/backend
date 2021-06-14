package com.compumundohipermegaweb.hefesto.api.brand.config

import com.compumundohipermegaweb.hefesto.api.brand.domain.action.CreateBrand
import com.compumundohipermegaweb.hefesto.api.brand.domain.action.DeleteBrand
import com.compumundohipermegaweb.hefesto.api.brand.domain.action.FindAllBrands
import com.compumundohipermegaweb.hefesto.api.brand.domain.action.UpdateBrand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.JpaBrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.repository.BrandDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrandConfig {

    @Bean
    fun findAllBrands(brandRepository: BrandRepository): FindAllBrands {
        return FindAllBrands(brandRepository)
    }

    @Bean
    fun deleteBrand(brandRepository: BrandRepository): DeleteBrand {
        return DeleteBrand(brandRepository)
    }

    @Bean
    fun createBrand(brandRepository: BrandRepository): CreateBrand {
        return CreateBrand(brandRepository)
    }

    @Bean
    fun updateBrand(brandRepository: BrandRepository): UpdateBrand {
        return UpdateBrand(brandRepository)
    }

    @Bean
    fun brandRepository(brandDao: BrandDao): BrandRepository {
        return JpaBrandRepository(brandDao)
    }
}