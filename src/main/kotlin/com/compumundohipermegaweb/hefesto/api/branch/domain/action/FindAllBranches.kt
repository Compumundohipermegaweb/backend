package com.compumundohipermegaweb.hefesto.api.branch.domain.action

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository

class FindAllBranches(private val branchRepository: BranchRepository) {

    operator fun invoke(): List<Branch> {
        return branchRepository.findAll()
    }
}