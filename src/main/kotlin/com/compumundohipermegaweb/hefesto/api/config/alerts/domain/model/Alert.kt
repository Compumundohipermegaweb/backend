package com.compumundohipermegaweb.hefesto.api.config.alerts.domain.model

import java.time.LocalTime

data class Alert(val id: Long,
                 val time: LocalTime,
                 val processDescription: String)
