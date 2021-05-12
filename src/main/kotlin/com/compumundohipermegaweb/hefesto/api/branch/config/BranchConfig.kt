package com.compumundohipermegaweb.hefesto.api.branch.config

import com.compumundohipermegaweb.hefesto.api.branch.domain.action.RegisterBranch
import com.compumundohipermegaweb.hefesto.api.branch.domain.repository.BranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.JpaBranchRepository
import com.compumundohipermegaweb.hefesto.api.branch.infra.repository.SpringDataBranchClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BranchConfig {
    @Bean
    fun registerBranch (branchRepository: BranchRepository): RegisterBranch{
        return RegisterBranch(branchRepository)
    }

    @Bean
    fun branchRepository (branchCrudRepository: SpringDataBranchClient): BranchRepository {
        return JpaBranchRepository(branchCrudRepository)
    }

}