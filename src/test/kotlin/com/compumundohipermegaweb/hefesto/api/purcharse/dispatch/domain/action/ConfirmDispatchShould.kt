package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class ConfirmDispatchShould {

    private lateinit var dispatchRepository: DispatchRepository
    private lateinit var confirmDispatch: ConfirmDispatch

    @Test
    fun `set the status to CONFIRMED`() {
        givenDispatchRepository()
        givenConfirmDispatch()

        whenConfirmingDispatch()

        thenDispatchIsConfirmed()
    }

    private fun givenDispatchRepository() {
        dispatchRepository = mock()
    }

    private fun givenConfirmDispatch() {
        confirmDispatch = ConfirmDispatch(dispatchRepository)
    }

    private fun whenConfirmingDispatch() {
        confirmDispatch(1L)
    }

    private fun thenDispatchIsConfirmed() {
        verify(dispatchRepository).confirm(1L)
    }
}