package com.compumundohipermegaweb.hefesto.api.payment.method.domain.action

import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class FindAllPaymentMethodTypesShould {

    private lateinit var findAllPaymentMethodTypes: FindAllPaymentMethodTypes

    private lateinit var paymentMethodTypes: List<PaymentMethod.Type>

    @Test
    fun `return all the existing types`() {
        givenFindAllPaymentMethodTypes()

        whenRetrievingAllPaymentMethodTypes()

        thenAllPaymentMethodTypesWhereReturned()
    }

    private fun givenFindAllPaymentMethodTypes() {
        findAllPaymentMethodTypes = FindAllPaymentMethodTypes()
    }

    private fun whenRetrievingAllPaymentMethodTypes() {
        paymentMethodTypes = findAllPaymentMethodTypes()
    }

    private fun thenAllPaymentMethodTypesWhereReturned() {
        then(paymentMethodTypes).containsExactly(
                PaymentMethod.Type.EFECTIVO,
                PaymentMethod.Type.TARJETA,
                PaymentMethod.Type.CUENTA_CORRIENTE,
                PaymentMethod.Type.ONLINE,
                PaymentMethod.Type.BANCARIO)
    }
}