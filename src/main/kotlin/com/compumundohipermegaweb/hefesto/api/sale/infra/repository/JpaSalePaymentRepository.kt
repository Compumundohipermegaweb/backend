package com.compumundohipermegaweb.hefesto.api.sale.infra.repository

import com.compumundohipermegaweb.hefesto.api.sale.domain.model.SalePayment
import com.compumundohipermegaweb.hefesto.api.sale.domain.repository.SalePaymentRepository
import com.compumundohipermegaweb.hefesto.api.sale.infra.representation.SalePaymentDao

class JpaSalePaymentRepository(private val springDataSalePaymentClient: SpringDataSalePaymentClient): SalePaymentRepository {
    override fun save(payments: SalePayment, saleId: Long): SalePayment {
        return springDataSalePaymentClient.save(payments.toPaymentDetailDao(saleId)).toPaymentDetail()
    }

    override fun saveAll(payments: List<SalePayment>, saleId: Long): List<SalePayment> {
        val salePaymentsDao = payments.map { it.toPaymentDetailDao(saleId) }
        return springDataSalePaymentClient.saveAll(salePaymentsDao).map { it.toPaymentDetail() }
    }

    override fun findBySaleId(saleID: Long): List<SalePayment> {
        return springDataSalePaymentClient.findBySaleId(saleID).map { it.toPaymentDetail() }
    }

    override fun delete(salePayment: SalePayment, saleId: Long) {
        springDataSalePaymentClient.delete(salePayment.toPaymentDetailDao(saleId))
    }

    private fun SalePayment.toPaymentDetailDao(saleId: Long): SalePaymentDao {
        return SalePaymentDao(id,saleId,paymentMethodId,cardId,lastDigits, email, subTotal)
    }

    private fun SalePaymentDao.toPaymentDetail(): SalePayment {
        return SalePayment(id,saleId,paymentMethodId,cardId,lastDigits, email, subTotal)
    }
}




