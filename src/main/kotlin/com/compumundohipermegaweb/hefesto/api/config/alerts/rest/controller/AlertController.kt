package com.compumundohipermegaweb.hefesto.api.config.alerts.rest.controller

import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.action.GetAllAlerts
import com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model.Alert
import com.compumundohipermegaweb.hefesto.api.config.alerts.rest.response.AlertResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/configurable")
class AlertController(private val getAllAlerts: GetAllAlerts) {

    @GetMapping
    @RequestMapping("/alerts")
    fun getAllAlerts(): ResponseEntity<List<AlertResponse>> {
        return ResponseEntity.ok(getAllAlerts.invoke().map { it.toAlertResponse() })
    }

    private fun Alert.toAlertResponse(): AlertResponse {
        return AlertResponse(id, time, processDescription)
    }
}


