package com.compumundohipermegaweb.hefesto.api.stock

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.User
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.action.StockMinimumAlert
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class StockMinimumAlertShould {

    private lateinit var stockRepository: StockRepository
    private lateinit var javaMailSender: JavaMailSender
    private lateinit var userRepository: UserRepository

    private lateinit var stockMinimumAlert: StockMinimumAlert

    @Test
    fun `register stock`() {
        givenStockRepository()
        givenEmailSender()
        givenUserRepository()
        givenStockMinimumAlert()

        whenAlertMinimumStock()

        thenTheMinimumStockIsAlerted()
    }

    private fun givenStockRepository() {
        stockRepository = mock()
        `when`(stockRepository.findAllInStock(1)).thenReturn(listOf(STOCK))
    }

    private fun givenUserRepository() {
        userRepository = mock()
        `when`(userRepository.findByCode("1")).thenReturn(listOf(USER))
    }

    private fun givenEmailSender() {
        javaMailSender = mock()
    }

    private fun givenStockMinimumAlert() {
        stockMinimumAlert = StockMinimumAlert(stockRepository, javaMailSender, userRepository)
    }

    private fun whenAlertMinimumStock() {
        stockMinimumAlert.invoke(1)
    }

    private fun thenTheMinimumStockIsAlerted() {
        verify(stockRepository).findAllInStock(1)
        verify(javaMailSender).send(ArgumentMatchers.any(SimpleMailMessage::class.java))
    }

    private companion object {
        private val STOCK = Stock(0L, "", 1L, 100, 100, 50, "")
        private val USER = User("1", "", "", Role.SUPERVISOR)
    }
}