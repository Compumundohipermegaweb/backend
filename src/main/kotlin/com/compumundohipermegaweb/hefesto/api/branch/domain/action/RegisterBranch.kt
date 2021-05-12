package com.compumundohipermegaweb.hefesto.api.branch.domain.action

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository

class RegisterBranch (private val branchRepository : BranchRepository){
    operator fun invoke(branch :Branch) = branchRepository.save(branch)

}
