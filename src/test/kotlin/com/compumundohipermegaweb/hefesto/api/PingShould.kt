package com.compumundohipermegaweb.hefesto.api

import com.compumundohipermegaweb.hefesto.api.ping.domain.action.Ping
import com.compumundohipermegaweb.hefesto.api.ping.domain.model.PingResponse
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class PingShould {
    private lateinit var ping: Ping
    private lateinit var pingResponse: PingResponse

    @Test
    fun `return the pom version`() {
        givenPingAction()

        whenPingTheApplication()

        thenVersionIsRetrieved()
    }

    private fun givenPingAction() {
        ping = Ping()
    }

    private fun whenPingTheApplication() {
        pingResponse = ping()
    }

    private fun thenVersionIsRetrieved() {
        then(pingResponse.version).isNotNull
    }
}