package com.compumundohipermegaweb.hefesto.api.branch.infra.repository

import com.compumundohipermegaweb.hefesto.api.branch.infra.representation.BranchRepresentation
import org.springframework.data.repository.CrudRepository

interface BranchDao : CrudRepository<BranchRepresentation, Long>
