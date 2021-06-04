package com.compumundohipermegaweb.hefesto.api.branch.domain.repository

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch

interface BranchRepository {
    fun save (branch : Branch) : Branch
    fun findAll(): List<Branch>
}
