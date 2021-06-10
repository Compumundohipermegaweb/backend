package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender


class StockMinimumAlert(private val stockRepository: StockRepository,
                        private val emailSender: JavaMailSender,
                        private val userRepository: UserRepository) {

    operator fun invoke(branchId: Long) {
        var stock = stockRepository.findAllInStock(branchId)
        stock = stock.filter { it.stockTotal <= it.minimumStock }
        val receivers = userRepository.findByCode(branchId.toString()).filter { it.role == Role.SUPERVISOR }.map { it.username }
        sendEmails(stock, receivers)
    }

    private fun sendEmails(stock: List<Stock>, receivers: List<String>) {
        val message = SimpleMailMessage()
        var text = ""
        message.setFrom("arg.hefesto.web@gmail.com")
        message.setSubject("ALerta de Stock minimo")
        text+= "Test integral\n"
        stock.forEach { text+=it.toString()  }
        message.setText(text)
        receivers.forEach {
            message.setTo(it)
            emailSender.send(message)
        }
    }
}