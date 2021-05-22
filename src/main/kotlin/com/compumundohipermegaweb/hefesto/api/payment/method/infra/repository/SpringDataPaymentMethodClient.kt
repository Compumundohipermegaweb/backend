package com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository

import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodDao
import org.springframework.data.repository.CrudRepository

interface SpringDataPaymentMethodClient : CrudRepository <PaymentMethodDao, Long> {

}
