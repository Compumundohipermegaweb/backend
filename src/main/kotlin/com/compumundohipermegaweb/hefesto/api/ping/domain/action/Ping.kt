package com.compumundohipermegaweb.hefesto.api.ping.domain.action

import com.compumundohipermegaweb.hefesto.api.ping.domain.model.PingResponse

class Ping {

    operator fun invoke(): PingResponse {
        return PingResponse("1.0")
    }

}
