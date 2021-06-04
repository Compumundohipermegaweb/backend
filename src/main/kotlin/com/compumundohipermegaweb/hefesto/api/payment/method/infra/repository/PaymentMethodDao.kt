package com.compumundohipermegaweb.hefesto.api.payment.method.infra.repository

import com.compumundohipermegaweb.hefesto.api.payment.method.infra.representation.PaymentMethodRepresentation
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import javax.transaction.Transactional

interface PaymentMethodDao : CrudRepository <PaymentMethodRepresentation, Long> {

    fun findAllByDeleted(deleted: Boolean): List<PaymentMethodRepresentation>

    @Transactional
    @Modifying
    @Query("update PaymentMethodRepresentation pm set pm.deleted = true where pm.id = :id")
    fun updateDeletedById(id: Long)
}
