package com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert

interface AlertRepository {
    fun getAllAlerts(): List<Alert>
}