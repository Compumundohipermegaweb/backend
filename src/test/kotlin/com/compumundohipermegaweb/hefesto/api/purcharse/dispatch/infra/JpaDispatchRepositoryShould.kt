package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.Dispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.reposiotory.DispatchDao
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.representation.DispatchRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaDispatchRepositoryShould {

    private lateinit var dispatchDao: DispatchDao
    private lateinit var dispatchRepository: DispatchRepository

    private lateinit var dispatchSaved: Dispatch

    @Test
    fun `save the dispatch`() {
        givenDispatchDao()
        givenDispatchRepository()

        whenSavingDispatch()

        thenDispatchHasBeenSaved()
    }

    private fun givenDispatchDao() {
        dispatchDao = mock()
        `when`(dispatchDao.save(DISPATCH_REPRESENTATION_TO_SAVE)).thenReturn(DISPATCH_REPRESENTATION_TO_SAVE)
    }

    private fun givenDispatchRepository() {
        dispatchRepository = JpaDispatchRepository(dispatchDao)
    }

    private fun whenSavingDispatch() {
        dispatchSaved = dispatchRepository.save(DISPATCH_TO_SAVE)
    }

    private fun thenDispatchHasBeenSaved() {
        verify(dispatchDao).save(DISPATCH_REPRESENTATION_TO_SAVE)
    }

    private companion object {
        val DISPATCH_TO_SAVE = Dispatch(0L, 1L, 0.0, Dispatch.Status.ACCEPTED)
        val DISPATCH_REPRESENTATION_TO_SAVE = DispatchRepresentation(0L, 1L, 0.0, Dispatch.Status.ACCEPTED.name)
    }
}