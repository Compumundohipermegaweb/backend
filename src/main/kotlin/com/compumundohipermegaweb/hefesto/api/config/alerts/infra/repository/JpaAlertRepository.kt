package com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository.AlertRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.representation.AlertRepresentation

class JpaAlertRepository(private val springDataAlertDao: SpringDataAlertDao): AlertRepository {
    override fun getAllAlerts(): List<Alert> {
        return springDataAlertDao.findAll().map { it.toAlert() }.toList()
    }

    private fun AlertRepresentation.toAlert(): Alert {
        return Alert(id, time, processDescription)
    }
}


