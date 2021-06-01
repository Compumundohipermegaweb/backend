package com.compumundohipermegaweb.hefesto.api.payment.method.config

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.*
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.DefaultPaymentMethodService
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.JpaPaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.PaymentMethodDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentMethodConfig {

    @Bean
    fun registerPaymentMethod(paymentMethodService: PaymentMethodService): RegisterPaymentMethod {
        return RegisterPaymentMethod(paymentMethodService)
    }

    @Bean
    fun getPaymentMethodsByClient(paymentMethodService: PaymentMethodService,
                                  clientService: ClientService,
                                  checkingAccountService: CheckingAccountService): GetPaymentMethodsByClient {
        return GetPaymentMethodsByClient(paymentMethodService, clientService, checkingAccountService)
    }

    @Bean
    fun paymentMethodService(paymentMethodRepository: PaymentMethodRepository): PaymentMethodService {
        return DefaultPaymentMethodService(paymentMethodRepository)
    }

    @Bean
    fun findAllPaymentMethods(paymentMethodRepository: PaymentMethodRepository): FindAllPaymentMethods {
        return FindAllPaymentMethods(paymentMethodRepository)
    }

    @Bean
    fun removePaymentMethod(paymentMethodRepository: PaymentMethodRepository): RemovePaymentMethod {
        return RemovePaymentMethod(paymentMethodRepository)
    }

    @Bean
    fun updatePaymentMethod(paymentMethodRepository: PaymentMethodRepository): UpdatePaymentMethod {
        return UpdatePaymentMethod(paymentMethodRepository)
    }

    @Bean
    fun findAllPaymentMethodTypes(): FindAllPaymentMethodTypes {
        return FindAllPaymentMethodTypes()
    }

    @Bean
    fun paymentMethodRepository(paymentMethodDao: PaymentMethodDao): PaymentMethodRepository {
        return JpaPaymentMethodRepository(paymentMethodDao)
    }
}