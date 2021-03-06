package com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository.AlertRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.representation.AlertRepresentation

class JpaAlertRepository(private val springDataAlertDao: SpringDataAlertDao): AlertRepository {

    override fun updateTimeAlert(alert: Alert): Alert? {
        val savedAlert = springDataAlertDao.getByAlertDescription(alert.alertDescription)
        if(savedAlert.isNotEmpty()) {
            savedAlert[0].time = alert.time
            return springDataAlertDao.save(savedAlert[0]).toAlert()
        }
        return null
    }

    override fun getAllAlerts(): List<Alert> {
        return springDataAlertDao.findAll().map { it.toAlert() }.toList()
    }

    override fun getByProcessDescription(processDescription: String): Alert? {
        val alertsFound = springDataAlertDao.getByAlertDescription(processDescription)
        if(alertsFound.isNotEmpty()){
            return alertsFound[0].toAlert()
        }
        return null
    }

    private fun AlertRepresentation.toAlert(): Alert {
        return Alert(id, time, alertDescription)
    }
}




