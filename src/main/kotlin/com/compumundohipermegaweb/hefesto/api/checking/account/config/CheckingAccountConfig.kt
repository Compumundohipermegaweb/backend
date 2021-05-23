package com.compumundohipermegaweb.hefesto.api.checking.account.config

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.DefaultCheckingAccountService
import com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository.JpaCheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository.SpringDataCheckingAccountDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CheckingAccountConfig {

    @Bean
    fun checkingAccountService(checkingAccountRepository: CheckingAccountRepository): CheckingAccountService {
        return DefaultCheckingAccountService(checkingAccountRepository)
    }

    @Bean
    fun checkingAccountRepository(springDataCheckingAccountDao: SpringDataCheckingAccountDao): CheckingAccountRepository {
        return JpaCheckingAccountRepository(springDataCheckingAccountDao)
    }
}