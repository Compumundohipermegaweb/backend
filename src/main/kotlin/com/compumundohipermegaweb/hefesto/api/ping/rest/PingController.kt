package com.compumundohipermegaweb.hefesto.api.ping.rest

import com.compumundohipermegaweb.hefesto.api.ping.domain.action.Ping
import com.compumundohipermegaweb.hefesto.api.ping.domain.model.PingResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ping")
class PingController(private val ping: Ping) {

    @GetMapping
    fun pingApplication(): PingResponse {
        return ping()
    }
}