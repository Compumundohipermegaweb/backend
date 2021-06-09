package com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository.AlertRepository

class GetAllAlerts(private val alertRepository: AlertRepository) {
    operator fun invoke(): List<Alert> {
        return alertRepository.getAllAlerts()
    }
}