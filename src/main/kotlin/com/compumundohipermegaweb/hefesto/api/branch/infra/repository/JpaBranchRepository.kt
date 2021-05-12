package com.compumundohipermegaweb.hefesto.api.branch.infra.repository

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.representation.BranchDao

class JpaBranchRepository (private val springDataBranchClient: SpringDataBranchClient): BranchRepository {
    override fun save(branch: Branch): Branch {
        val branchDao = BranchDao (branch.id,branch.branch, branch.address, branch.postalCode, branch.email, branch.contactNumber,branch.attentionSchedule)
        return springDataBranchClient.save(branchDao).toBranch()
    }
    private fun BranchDao.toBranch() = Branch(id, branch, address, postalCode, email ,contactNumber, attentionSchedule)
}
