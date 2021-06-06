package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender


class StockMinimumAlert(
    private val stockRepository: StockRepository,
    @Autowired
    private val emailSender: JavaMailSender) {

    operator fun invoke(branchId: Long) {
        var stock = stockRepository.findAllInStock(branchId)
        stock = stock.filter { it.stockTotal <= it.minimumStock }
        sendEmails(stock)
    }

    private fun sendEmails(stock: List<Stock>) {
        val message = SimpleMailMessage()
        var text = ""
        message.setFrom("arg.hefesto.web@gmail.com")
        message.setTo("aron_2309@gmail.com")
        text+= "testeando el email sender"
        stock.forEach { text+=it.toString()  }
        message.setText(text)
        emailSender.send(message)
    }
}