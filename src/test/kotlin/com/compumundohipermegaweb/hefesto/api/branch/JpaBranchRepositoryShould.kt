package com.compumundohipermegaweb.hefesto.api.branch

import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.JpaBranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.SpringDataBranchClient
import com.compumundohipermegaweb.hefesto.api.branch.infra.representation.BranchDao
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`


class JpaBranchRepositoryShould {
        private lateinit var sprintDataBranchClient : SpringDataBranchClient
        private lateinit var branchRepository: BranchRepository
        private lateinit var registeredBranch: Branch

    @Test
    fun `save the input`(){
        givenBranchCrudRepository()
        givenBranchRepository()
        whenRegisteringBranch()
        thenBranchRegistered()

    }
    private fun givenBranchRepository() {
        branchRepository = JpaBranchRepository(sprintDataBranchClient)
    }

    private fun givenBranchCrudRepository() {
        sprintDataBranchClient = mock()
        `when`(sprintDataBranchClient.save(BRANCH_DAO)).thenReturn(BRANCH_DAO)
    }

    private fun whenRegisteringBranch() {
        registeredBranch = branchRepository.save(BRANCH)
    }

    private fun thenBranchRegistered() {
        verify(sprintDataBranchClient).save(BRANCH_DAO)
        then(registeredBranch).isNotNull
    }

    companion object {
    val BRANCH_DAO = BranchDao(0L,"SUC01","address","CP1111","xxxxx@xxx.com","9999999","9 a 18hs")
    val BRANCH = Branch(0L,"SUC01","address","CP1111","xxxxx@xxx.com","9999999","9 a 18hs")
}

}