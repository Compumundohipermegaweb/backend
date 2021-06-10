package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Role
import com.compumundohipermegaweb.hefesto.api.authentication.domain.repository.UserRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper


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
        val email = emailSender.createMimeMessage()
        val helper = MimeMessageHelper(email)

        helper.setFrom("arg.hefesto.web@gmail.com")
        helper.setSubject("Alerta de Stock minimo")

        val text = StringBuilder()

        text.append("<table border='1'>")
        text.append("<tbody>")
        text.append("<tr>")
        text.append("<th>Sku</th>")
        text.append("<th>Descripción</th>")
        text.append("<th>Stock restante</th>")
        text.append("</tr>")

        stock.forEach {
            text.append("<tr>")
            text.append("<td><center>").append(it.sku).append("</td>")
            text.append("<td><center>").append(it.itemDescription).append("</td>")
            text.append("<td><center>").append(it.stockTotal).append("</td>")
            text.append("</tr>")
        }

        text.append("</tbody>")
        text.append("</table>")

        helper.setText(text.toString(), true)
        receivers.forEach {
            helper.setTo(it)
            emailSender.send(email)
        }
    }
}