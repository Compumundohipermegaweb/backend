package com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository

import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodRepresentation
import org.springframework.data.repository.CrudRepository

interface PaymentMethodDao : CrudRepository <PaymentMethodRepresentation, Long>
