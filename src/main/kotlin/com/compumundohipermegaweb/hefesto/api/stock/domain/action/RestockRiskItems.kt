package com.compumundohipermegaweb.hefesto.api.stock.domain.action

import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.model.PurchaseOrder
import com.compumundohipermegaweb.hefesto.api.purcharse.order.domain.repository.PurchaseOrderRepository
import com.compumundohipermegaweb.hefesto.api.stock.domain.model.Stock
import com.compumundohipermegaweb.hefesto.api.stock.domain.repository.StockRepository
import com.compumundohipermegaweb.hefesto.api.supplier.domain.service.SupplierService
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import javax.mail.internet.MimeMessage

class RestockRiskItems(private val stockRepository: StockRepository,
                       private val purchaseOrderRepository: PurchaseOrderRepository,
                       private val mailSender: JavaMailSender,
                       private val supplierService: SupplierService) {

    operator fun invoke(): List<String> {
        val purchaseOrders = generatePurchaseOrders()
        val groupedOrders = purchaseOrders.groupBy { it.supplier }
        val email = sendOrders(groupedOrders)
        return email.map { it.content.toString() }
    }

    private fun generatePurchaseOrders(): List<PurchaseOrder> {
        val lowStock = stockRepository.findLowStock()
        val purchaseOrders = mutableListOf<PurchaseOrder>()

        lowStock.forEach {
            if (!purchaseOrderRepository.exists(it.sku)) {
                val purchaseOrder = it.asPurchaseOrder(it.branchId)
                purchaseOrders.add(purchaseOrderRepository.save(purchaseOrder))
            }
        }

        return purchaseOrders
    }

    private fun sendOrders(groupedOrders: Map<String, List<PurchaseOrder>>): List<MimeMessage> {
        val emailSent = mutableListOf<MimeMessage>()
        groupedOrders.forEach {
            val email = createEmail(it.key, it.value)
            mailSender.send(email)
            emailSent.add(email)
        }
        return emailSent
    }

    private fun createEmail(supplierEmail: String, purchaseOrders: List<PurchaseOrder>): MimeMessage {
        val email = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(email)

        helper.setTo(supplierEmail)
        helper.setFrom("hefesto@ferreterias.com")
        helper.setSubject("Orden de compra")

        val text = generateEmailText(purchaseOrders)

        helper.setText(text)

        return email
    }

    private fun generateEmailText(purchaseOrders: List<PurchaseOrder>) =
            """
                <table>
                    <tr>
                        <th>Sucursal</th>
                        <th>Orden de compra Nro.</th>
                        <th>SKU</th>
                        <th>Cantidad</th>
                    </tr>
                ${
                purchaseOrders.groupBy { it.branchId }.map {
                    """
                    <tr>
                        ${it.value.map { purchaseOrder ->
                            """
                            <td>${it.key}</>
                            <td>${purchaseOrder.id}</td>
                            <td>${purchaseOrder.sku}</td>
                            <td>${purchaseOrder.amount}</td>
                            """.trimIndent()
                    }}
                    </tr>
                """.trimIndent()
                }
            }
                </table>
                """.trimIndent()

    private fun Stock.asPurchaseOrder(branchId: Long): PurchaseOrder {
        val supplier = supplierService.findBySuppliedSku(sku)
        return PurchaseOrder(0L, branchId, sku, securityStock, supplier!!.email, PurchaseOrder.Status.PENDING)
    }
}