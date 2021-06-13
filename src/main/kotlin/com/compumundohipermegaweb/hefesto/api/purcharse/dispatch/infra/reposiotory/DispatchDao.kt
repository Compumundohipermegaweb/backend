package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.reposiotory

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.infra.representation.DispatchRepresentation
import org.springframework.data.repository.CrudRepository

interface DispatchDao: CrudRepository<DispatchRepresentation, Long> {

}
