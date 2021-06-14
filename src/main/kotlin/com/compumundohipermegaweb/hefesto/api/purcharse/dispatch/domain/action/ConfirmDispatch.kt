package com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.dispatch.domain.repository.DispatchRepository

class ConfirmDispatch(private val dispatchRepository: DispatchRepository) {

    operator fun invoke(dispatchId: Long) {
        dispatchRepository.confirm(dispatchId)
    }
}