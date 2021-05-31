package com.compumundohipermegaweb.hefesto.api.cash.config

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.*
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.JpaCashRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.JpaCashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.SpringCashDao
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.SpringCashStartEndDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CashConfig {

    @Bean
    fun openCash(cashRepository: CashRepository, cashStartEndRepository: CashStartEndRepository): OpenCash {
        return OpenCash(cashRepository, cashStartEndRepository)
    }

    @Bean
    fun registerCash(cashRepository: CashRepository): RegisterCash {
        return RegisterCash(cashRepository)
    }

    @Bean
    fun closeCash(cashRepository: CashRepository, cashStartEndRepository: CashStartEndRepository): CloseCash {
        return CloseCash(cashRepository, cashStartEndRepository)
    }

    @Bean
    fun getAllRegisterCash(cashRepository: CashRepository): GetAllRegisterCash {
        return GetAllRegisterCash(cashRepository)
    }

    @Bean
    fun getCashByUserId(cashStartEndRepository: CashStartEndRepository): GetCashByUserId {
        return GetCashByUserId(cashStartEndRepository)
    }

    @Bean
    fun cashRepository(springCashDao: SpringCashDao): CashRepository {
        return JpaCashRepository(springCashDao)
    }

    @Bean
    fun cashStartEndRepository(springCashStartEndDao: SpringCashStartEndDao): CashStartEndRepository {
        return JpaCashStartEndRepository(springCashStartEndDao)
    }
}