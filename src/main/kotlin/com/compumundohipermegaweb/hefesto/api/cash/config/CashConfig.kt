package com.compumundohipermegaweb.hefesto.api.cash.config

import com.compumundohipermegaweb.hefesto.api.cash.domain.action.*
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.CashStartEndRepository
import com.compumundohipermegaweb.hefesto.api.cash.domain.repository.TotalMovementRepository
import com.compumundohipermegaweb.hefesto.api.cash.infra.repository.*
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.client.domain.repository.ClientRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SaleRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CashConfig {

    @Bean
    fun openCash(cashRepository: CashRepository, cashStartEndRepository: CashStartEndRepository, cashMovementRepository: CashMovementRepository): OpenCash {
        return OpenCash(cashRepository, cashStartEndRepository, cashMovementRepository)
    }

    @Bean
    fun registerCash(cashRepository: CashRepository): RegisterCash {
        return RegisterCash(cashRepository)
    }

    @Bean
    fun registerCashMovement(cashMovementRepository: CashMovementRepository): RegisterMovement {
        return RegisterMovement(cashMovementRepository)
    }

    @Bean
    fun closeCash(cashRepository: CashRepository, cashStartEndRepository: CashStartEndRepository, cashMovementRepository: CashMovementRepository): CloseCash {
        return CloseCash(cashRepository, cashStartEndRepository, cashMovementRepository)
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
    fun getAllIncomes(cashMovementRepository: CashMovementRepository, saleRepository: SaleRepository, salePaymentRepository: SalePaymentRepository, clientRepository: ClientRepository): GetAllIncomes {
        return GetAllIncomes(cashMovementRepository, saleRepository, salePaymentRepository, clientRepository)
    }

    @Bean
    fun getAllExpenses(cashMovementRepository: CashMovementRepository, paymentMethodRepository: PaymentMethodRepository): GetAllExpenses {
        return GetAllExpenses(cashMovementRepository, paymentMethodRepository)
    }

    @Bean
    fun updatePaymentDetails(cashMovementRepository: CashMovementRepository, salePaymentRepository: SalePaymentRepository, saleRepository: SaleRepository, checkingAccountRepository: CheckingAccountRepository): UpdatePaymentDetails {
        return UpdatePaymentDetails(cashMovementRepository, salePaymentRepository, saleRepository, checkingAccountRepository)
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

    @Bean
    fun totalMovementRepository(springTotalMovementDao: SpringTotalMovementDao): TotalMovementRepository {
        return JpaTotalMovementRepository(springTotalMovementDao)
    }

    @Bean
    fun getTotalMovement(totalMovementRepository: TotalMovementRepository): GetTotalMovement {
        return GetTotalMovement(totalMovementRepository)
    }
}