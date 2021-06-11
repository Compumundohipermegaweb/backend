package com.compumundohipermegaweb.hefesto.api.config.alerts.config

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.GetAllAlerts
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.UpdateAlert
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.repository.AlertRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository.JpaAlertRepository
import com.compumundohipermegaweb.hefesto.api.config.alerts.infra.repository.SpringDataAlertDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AlertConfig {

    @Bean
    fun getAllAlerts(alertRepository: AlertRepository): GetAllAlerts {
        return GetAllAlerts(alertRepository)
    }

    @Bean
    fun updateAlert(alertRepository: AlertRepository): UpdateAlert {
        return UpdateAlert(alertRepository)
    }

    @Bean
    fun alertRepository(springDataAlertDao: SpringDataAlertDao): AlertRepository{
        return JpaAlertRepository(springDataAlertDao)
    }
}