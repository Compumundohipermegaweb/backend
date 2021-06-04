package com.compumundohipermegaweb.hefesto.api.branch

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.JpaBranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.BranchDao
import com.compumundohipermegaweb.hefesto.api.branch.infra.representation.BranchRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`


class JpaBranchRepositoryShould {
        private lateinit var branchDao : BranchDao
        private lateinit var branchRepository: BranchRepository
        private lateinit var registeredBranch: Branch

        private lateinit var branchesFound: List<Branch>

    @Test
    fun `save the input`() {
        givenBranchCrudRepository()
        givenBranchRepository()

        whenRegisteringBranch()

        thenBranchRegistered()
    }

    @Test
    fun `find all branches`() {
        givenBranchCrudRepository()
        givenBranchRepository()

        whenFindingAllBranches()

        thenBranchesWhereFound()
    }

    private fun givenBranchRepository() {
        branchRepository = JpaBranchRepository(branchDao)
    }

    private fun givenBranchCrudRepository() {
        branchDao = mock()
        `when`(branchDao.save(BRANCH_DAO)).thenReturn(BRANCH_DAO)
    }

    private fun whenRegisteringBranch() {
        registeredBranch = branchRepository.save(BRANCH)
    }

    private fun whenFindingAllBranches() {
        branchesFound = branchRepository.findAll()
    }

    private fun thenBranchRegistered() {
        verify(branchDao).save(BRANCH_DAO)
        then(registeredBranch).isNotNull
    }

    private fun thenBranchesWhereFound() {
        verify(branchDao).findAll()
    }

    companion object {
        val BRANCH_DAO = BranchRepresentation(0L,"SUC01","address","CP1111","xxxxx@xxx.com","9999999","9 a 18hs")
    val BRANCH = Branch(0L,"SUC01","address","CP1111","xxxxx@xxx.com","9999999","9 a 18hs")
}

}