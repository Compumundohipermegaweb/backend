package com.compumundohipermegaweb.hefesto.api.payment.method.config

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.RegisterPaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.repository.PaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.JpaPaymentMethodRepository
import com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository.SpringDataPaymentMethodClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentMethodConfig {

    @Bean
    fun registerPaymentMethod( paymentMethodRepository: PaymentMethodRepository): RegisterPaymentMethod {
        return RegisterPaymentMethod(paymentMethodRepository)
    }

    @Bean
    fun paymentMethodRepository( paymentCrudRepository: SpringDataPaymentMethodClient): PaymentMethodRepository {
        return JpaPaymentMethodRepository(paymentCrudRepository)
    }
}