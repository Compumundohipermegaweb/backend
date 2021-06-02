package com.compumundohipermegaweb.hefesto.api.cash.config

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.*
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.*
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
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
    fun getAllCashMovements(cashMovementRepository: CashMovementRepository): GetAllCashMovements {
        return GetAllCashMovements(cashMovementRepository)
    }

    @Bean
    fun getAllIncomes(cashMovementRepository: CashMovementRepository, saleRepository: SaleRepository, salePaymentRepository: SalePaymentRepository): GetAllIncomes {
        return GetAllIncomes(cashMovementRepository, saleRepository, salePaymentRepository)
    }

    @Bean
    fun getAllExpenses(cashMovementRepository: CashMovementRepository, paymentMethodRepository: PaymentMethodRepository): GetAllExpenses {
        return GetAllExpenses(cashMovementRepository, paymentMethodRepository)
    }

    @Bean
    fun cashRepository(springCashDao: SpringCashDao): CashRepository {
        return JpaCashRepository(springCashDao)
    }

    @Bean
    fun cashStartEndRepository(springCashStartEndDao: SpringCashStartEndDao): CashStartEndRepository {
        return JpaCashStartEndRepository(springCashStartEndDao)
    }

    @Bean
    fun cashMovementRepository(springCashMovementDao: SpringCashMovementDao): CashMovementRepository {
        return JpaCashMovementRepository(springCashMovementDao)
    }
}