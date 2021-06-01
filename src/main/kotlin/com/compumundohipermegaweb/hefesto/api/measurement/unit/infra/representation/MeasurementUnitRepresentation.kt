package com.compumundohipermegaweb.hefesto.api.measurement.unit.infra.representation

import javax.persistence.*

@Entity
@Table(name = "MEASUREMENT_UNIT")
data class MeasurementUnitRepresentation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,

        @Column(name = "NAME")
        val name: String,

        @Column(name = "DESCRIPTION")
        val description: String
)
