package com.compumundohipermegaweb.hefesto.api.discount.config

import com.compumundohipermegaweb.hefesto.api.discount.domain.repositorty.DiscountRepository
import com.compumundohipermegaweb.hefesto.api.discount.infra.repository.DiscountDao
import com.compumundohipermegaweb.hefesto.api.discount.infra.repository.JpaDiscountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscountConfig {

    @Bean
    fun discountRepository(discountDao: DiscountDao): DiscountRepository {
        return JpaDiscountRepository(discountDao)
    }
}