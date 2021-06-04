package com.compumundohipermegaweb.hefesto.api.branch.infra.repository

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.representation.BranchRepresentation

class JpaBranchRepository (private val branchDao: BranchDao): BranchRepository {
    override fun save(branch: Branch): Branch {
        val branchDao = BranchRepresentation (branch.id,branch.branch, branch.address, branch.postalCode, branch.email, branch.contactNumber,branch.attentionSchedule)
        return this.branchDao.save(branchDao).toBranch()
    }

    override fun findAll(): List<Branch> {
        val branchRepresentations = branchDao.findAll()
        return branchRepresentations.map { it.toBranch() }
    }

    private fun BranchRepresentation.toBranch() = Branch(id, branch, address, postalCode, email ,contactNumber, attentionSchedule)
}
