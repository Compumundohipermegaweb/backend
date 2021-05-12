package com.compumundohipermegaweb.hefesto.api.branch.infra.repository

import com.compumundohipermegaweb.hefesto.api.branch.infra.representation.BranchDao
import org.springframework.data.repository.CrudRepository

interface SpringDataBranchClient : CrudRepository<BranchDao, Long> {

}
