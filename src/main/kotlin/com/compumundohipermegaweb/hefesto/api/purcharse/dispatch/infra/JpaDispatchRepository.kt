package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.model.Dispatch
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.reposiotory.DispatchDao
import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.representation.DispatchRepresentation

class JpaDispatchRepository(private val dispatchDao: DispatchDao): DispatchRepository {

    override fun save(dispatch: Dispatch): Dispatch {
        val representation = dispatch.toRepresentation()
        return dispatchDao.save(representation).toDispatch()
    }

    override fun findAll(): List<Dispatch> {
        val representations = dispatchDao.findAll()
        return representations.map { it.toDispatch() }
    }

    override fun confirm(id: Long) {
        dispatchDao.updateStatus(id, Dispatch.Status.CONFIRMED.name)
    }

    private fun Dispatch.toRepresentation() = DispatchRepresentation(id, supplierId, totalCost, status.name)

    private fun DispatchRepresentation.toDispatch() = Dispatch(id, supplierId, totalCost, Dispatch.Status.valueOf(status))
}
