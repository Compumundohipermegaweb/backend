package com.compumundohipermegaweb.hefesto.api.cash.infra.representation

import org.hibernate.annotations.Immutable
import org.hibernate.annotations.Subselect
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id


@Entity
@Immutable
@Subselect("SELECT * FROM VW_TOTAL_MOVEMENT")
class TotalMovementRepresentation (
        @Id @Column(name = "BRANCH_ID")
        val branchId: Long,
        @Column(name = "CASH_ID")
        val cashId: Long,
        @Column(name = "CASH_START_END_ID")
        val cashStartEndId: Long,
        @Column(name = "DATE")
        val dateTime: Date,
        @Column(name = "MOVEMENT_ID")
        val movementType: String,
        @Column(name = "PAYMENT_METHOD")
        val paymentMethod: String,
        @Column(name = "TOTAL")
        val total: Double)
