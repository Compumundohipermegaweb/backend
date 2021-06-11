package com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository

import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.representation.AlertRepresentation
import org.springframework.data.repository.CrudRepository

interface SpringDataAlertDao: CrudRepository<AlertRepresentation, Long> {
    fun getByAlertDescription(alertDescription: String): List<AlertRepresentation>
}