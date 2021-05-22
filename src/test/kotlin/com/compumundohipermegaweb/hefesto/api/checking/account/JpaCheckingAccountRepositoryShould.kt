package com.compumundohipermegaweb.hefesto.api.checking.account

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.repository.CheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository.JpaCheckingAccountRepository
import com.compumundohipermegaweb.hefesto.api.checking.account.infra.repository.SpringDataCheckingAccountDao
import com.compumundohipermegaweb.hefesto.api.checking.account.infra.representation.CheckingAccountRepresentation
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class JpaCheckingAccountRepositoryShould {
    private lateinit var springDataCheckingAccountDao: SpringDataCheckingAccountDao
    private lateinit var checkingAccountRepository: CheckingAccountRepository

    private var checkingAccountFound: CheckingAccount? = null

    @Test
    fun `find checking account by client id`(){
        givenSpringCheckingAccountRepository()
        givenCheckingAccountRepository()

        whenFindingTheCheckingAccountById(0L)

        thenCheckingAccountIsFound()
    }

    @Test
    fun `the found checking account by client id is returned`(){
        givenSpringCheckingAccountRepository()
        givenCheckingAccountRepository()

        whenFindingTheCheckingAccountById(0L)

        thenCheckingAccountFoundedIsReturned()
    }

    @Test
    fun `not find checking account by client id`(){
        givenSpringCheckingAccountRepository()
        givenCheckingAccountRepository()

        whenFindingTheCheckingAccountById(1L)

        thenCheckingAccountIsNotFound()
    }

    private fun givenSpringCheckingAccountRepository() {
        springDataCheckingAccountDao = mock()
        `when`(springDataCheckingAccountDao.findByClientId(0L)).thenReturn(CHECKING_ACCOUNT_REPRESENTATION)
        `when`(springDataCheckingAccountDao.findByClientId(1L)).thenReturn(null)
    }

    private fun givenCheckingAccountRepository() {
        checkingAccountRepository = JpaCheckingAccountRepository(springDataCheckingAccountDao)
    }

    private fun whenFindingTheCheckingAccountById(clientId: Long) {
        checkingAccountFound = checkingAccountRepository.findCheckingAccountByClientId(clientId)
    }

    private fun thenCheckingAccountIsFound() {
        verify(springDataCheckingAccountDao).findByClientId(0L)
        then(checkingAccountFound).isNotNull
    }

    private fun thenCheckingAccountFoundedIsReturned() {
        then(checkingAccountFound).isEqualTo(CHECKING_ACCOUNT)
    }

    private fun thenCheckingAccountIsNotFound() {
        verify(springDataCheckingAccountDao).findByClientId(1L)
        then(checkingAccountFound).isNull()
    }

    private companion object {
        val CHECKING_ACCOUNT_REPRESENTATION = CheckingAccountRepresentation(0L, 1L, 0.0, 0.0, 0.0)
        val CHECKING_ACCOUNT = CheckingAccount(0L, 1L, 0.0, 0.0, 0.0)

    }
}