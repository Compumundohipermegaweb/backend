package com.compumundohipermegaweb.hefesto.api.paymentMethod.config

import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.action.RegisterPaymentMethod
import com.compumundohipermegaweb.hefesto.api.paymentMethod.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.repository.JpaPaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.repository.SpringDataPaymentMethodClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentMethodConfig {

    @Bean
    fun registerPaymentMethod( paymentMethodRepository: PaymentMethodRepository):RegisterPaymentMethod{
        return RegisterPaymentMethod(paymentMethodRepository)
    }

    @Bean
    fun paymentMethodRepository( paymentCrudRepository: SpringDataPaymentMethodClient): PaymentMethodRepository{
        return JpaPaymentMethodRepository(paymentCrudRepository)
    }
}