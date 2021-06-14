package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.Dispatch

interface DispatchRepository {

    fun save(dispatch: Dispatch): Dispatch
    fun findAll(): List<Dispatch>
    fun confirm(id: Long)
}
