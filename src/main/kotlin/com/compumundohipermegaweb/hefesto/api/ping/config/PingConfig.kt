package com.compumundohipermegaweb.hefesto.api.ping.config

import com.compumundohipermegaweb.hefesto.api.ping.domain.action.Ping
import com.compumundohipermegaweb.hefesto.api.ping.rest.PingController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PingConfig {

    @Bean
    open fun pingController(ping: Ping): PingController {
        return PingController(ping)
    }

    @Bean
    open fun ping(): Ping {
        return Ping()
    }
}