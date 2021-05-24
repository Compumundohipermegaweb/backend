package com.compumundohipermegaweb.hefesto.api.rejected.sale.config

import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.repository.RejectedSaleRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service.DefaultRejectedSaleService
import com.compumundohipermegaweb.hefesto.api.rejected.sale.domain.service.RejectedSaleService
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.JpaRejectedItemDetailRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.JpaRejectedSaleRepository
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.SpringDataRejectedSaleDao
import com.compumundohipermegaweb.hefesto.api.rejected.sale.infra.repository.SpringRejectedItemDetailDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RejectedSaleConfig {

    @Bean
    fun rejectedSaleService(rejectedSaleRepository: RejectedSaleRepository, rejectedItemDetailRepository: RejectedItemDetailRepository): RejectedSaleService {
        return DefaultRejectedSaleService(rejectedSaleRepository, rejectedItemDetailRepository)
    }

    @Bean
    fun rejectedSaleRepository(springDataRejectedSaleDao: SpringDataRejectedSaleDao): RejectedSaleRepository {
        return JpaRejectedSaleRepository(springDataRejectedSaleDao)
    }

    @Bean
    fun rejectedItemDetailRepository(springRejectedItemDetailDao: SpringRejectedItemDetailDao): RejectedItemDetailRepository {
        return JpaRejectedItemDetailRepository(springRejectedItemDetailDao)
    }
}