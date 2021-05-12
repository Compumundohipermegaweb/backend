package com.compumundohipermegaweb.hefesto.api.branch

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.RegisterBranch
import com.compumundohipermegaweb.hefesto.api.branch.domain.model.Branch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class RegisterBranchShould {

    private lateinit var branchRepository: BranchRepository
    private lateinit var branchRegistered : Branch
    private lateinit var registerBranch: RegisterBranch

    @Test
    fun `register a branch`(){
        givenBranchRepository()
        givenRegisterBranch()
        whenRegisteringBranch()
        thenTheBranchIsRegisteredSuccessfully()

    }

    private fun givenBranchRepository() {
        branchRepository = mock()
    }

    private fun givenRegisterBranch() {
        registerBranch = RegisterBranch(branchRepository)
    }

    private fun whenRegisteringBranch() {
        branchRegistered = registerBranch(BRANCH)
    }

    private fun thenTheBranchIsRegisteredSuccessfully() {
        `when`(branchRepository.save(BRANCH)).thenReturn(BRANCH)
    }

    companion object{
        val BRANCH = Branch(0L,"SUC01","xxx","1111","xxx@xxx.com","9999","9 a 18hs")
    }
}