package com.compumundohipermegaweb.hefesto.api.config.alerts.infra.representation

import org.hibernate.envers.Audited
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "ALERT")
@Audited
data class AlertRepresentation(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") val id: Long,
                               @Column(name = "TIME") val time: LocalTime,
                               @Column(name = "PROCESS_DESCRIPTION") val processDescription: String)
