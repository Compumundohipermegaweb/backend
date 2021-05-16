package com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.repository

import com.compumundohipermegaweb.hefesto.api.paymentMethod.infra.representation.PaymentMethodDao
import org.springframework.data.repository.CrudRepository

interface SpringDataPaymentMethodClient : CrudRepository <PaymentMethodDao, Long> {

}
