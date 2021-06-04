package com.compumundohipermegaweb.hefesto.api.branch.domain.action

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class FindAllBranchesShould {

    private lateinit var branchRepository: BranchRepository
    private lateinit var findAllBranches: FindAllBranches

    private lateinit var branchesFound: List<Branch>

    @Test
    fun `find all existing branches`() {
        givenBranchrepository()
        givenFindAllBranches()

        whenFindingAllBranches()

        thenBranchesWhereFound()
    }

    private fun givenBranchrepository() {
        branchRepository = mock()
    }

    private fun givenFindAllBranches() {
        findAllBranches = FindAllBranches(branchRepository)
    }

    private fun whenFindingAllBranches() {
        branchesFound = findAllBranches()
    }

    private fun thenBranchesWhereFound() {
        verify(branchRepository).findAll()
    }
}