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

    operator fun invoke() {
        val purchaseOrders = generatePurchaseOrders()
        val groupedOrders = purchaseOrders.groupBy { it.supplier }
        sendOrders(groupedOrders)
    }

    private fun generatePurchaseOrders(): List<PurchaseOrder> {
        val lowStock = stockRepository.findLowStock()
        val purchaseOrders = mutableListOf<PurchaseOrder>()

        lowStock.forEach {
            if (!purchaseOrderRepository.exists(it.sku)) {
                val purchaseOrder = it.asPurchaseOrder()
                purchaseOrders.add(purchaseOrderRepository.save(purchaseOrder))
            }
        }

        return purchaseOrders
    }

    private fun sendOrders(groupedOrders: Map<String, List<PurchaseOrder>>) {
        groupedOrders.forEach {
            val email = createEmail(it.key, it.value)
            mailSender.send(email)
        }
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
                        <th>Orden de compra Nro.</th>
                        <th>SKU</th>
                        <th>Cantidad</th>
                    </tr>
                ${
                purchaseOrders.map {
                    """
                    <tr>
                        <td>${it.id}</td>
                        <td>${it.sku}</td>
                        <td>${it.amount}</td>
                    </tr>
                """.trimIndent()
                }
            }
                </table>
                """.trimIndent()

    private fun Stock.asPurchaseOrder(): PurchaseOrder {
        val supplier = supplierService.findBySuppliedSku(sku)
        return PurchaseOrder(0L, sku, securityStock, supplier!!.email)
    }
}