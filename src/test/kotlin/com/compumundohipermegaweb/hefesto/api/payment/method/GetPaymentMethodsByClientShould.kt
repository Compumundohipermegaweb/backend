package com.compumundohipermegaweb.hefesto.api.payment.method

import com.compumundohipermegaweb.hefesto.api.checking.account.domain.model.CheckingAccount
import com.compumundohipermegaweb.hefesto.api.checking.account.domain.service.CheckingAccountService
import com.compumundohipermegaweb.hefesto.api.client.domain.model.Client
import com.compumundohipermegaweb.hefesto.api.client.domain.service.ClientService
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.action.GetPaymentMethodsByClient
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.model.PaymentMethod
import com.compumundohipermegaweb.hefesto.api.payment.method.domain.service.PaymentMethodService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`

class GetPaymentMethodsByClientShould {

    private lateinit var paymentMethodService: PaymentMethodService
    private lateinit var clientService: ClientService
    private lateinit var checkingAccountService: CheckingAccountService
    private lateinit var getPaymentMethodsByClient: GetPaymentMethodsByClient

    private lateinit var paymentMethodsByClient: List<PaymentMethod>

    @Test
    fun `get payment methods by active client whit checking account`() {
        givenPaymentMethodService()
        givenClientService()
        givenCheckingAccountService()
        givenGetPaymentMethodsByClient()

        whenGettingThePaymentMethodByClient(ACTIVE_CLIENT.id)

        thenThePaymentMethodsHasFoundForActiveClient()
    }

    @Test
    fun `return the payment methods for active client whit checking account`() {
        givenPaymentMethodService()
        givenClientService()
        givenCheckingAccountService()
        givenGetPaymentMethodsByClient()

        whenGettingThePaymentMethodByClient(ACTIVE_CLIENT.id)

        thenThePaymentMethodsForActiveHasReturned()
    }

    @Test
    fun `get payment methods by defaulter client`() {
        givenPaymentMethodService()
        givenClientService()
        givenCheckingAccountService()
        givenGetPaymentMethodsByClient()

        whenGettingThePaymentMethodByClient(DEFAULTER_CLIENT.id)

        thenThePaymentMethodsHasFoundForDefaulterClient()
    }

    @Test
    fun `return the payment methods for defaulter client`() {
        givenPaymentMethodService()
        givenClientService()
        givenCheckingAccountService()
        givenGetPaymentMethodsByClient()

        whenGettingThePaymentMethodByClient(DEFAULTER_CLIENT.id)

        thenThePaymentMethodsForDefaulterClientHasReturned()
    }

    @Test
    fun `get payment methods by client without checking account`() {
        givenPaymentMethodService()
        givenClientService()
        givenCheckingAccountService()
        givenGetPaymentMethodsByClient()

        whenGettingThePaymentMethodByClient(CLIENT_WITHOUT_CHECKING_ACCOUNT.id)

        thenThePaymentMethodsHasFoundForClientWithoutCheckingAccount()
    }

    @Test
    fun `return the payment methods for client without checking account`() {
        givenPaymentMethodService()
        givenClientService()
        givenCheckingAccountService()
        givenGetPaymentMethodsByClient()

        whenGettingThePaymentMethodByClient(CLIENT_WITHOUT_CHECKING_ACCOUNT.id)

        thenThePaymentMethodsForClientWithoutCheckingAccountHasReturned()
    }

    private fun givenPaymentMethodService() {
        paymentMethodService = mock()
        `when`(paymentMethodService.findAllPaymentMethod()).thenReturn(listOf(ACTIVE_PAYMENT_METHOD, INACTIVE_PAYMENT_METHOD, ANOTHER_PAYMENT_METHOD))
    }

    private fun givenClientService() {
        clientService = mock()
        `when`(clientService.findById(ACTIVE_CLIENT.id)).thenReturn(ACTIVE_CLIENT)
        `when`(clientService.findById(DEFAULTER_CLIENT.id)).thenReturn(DEFAULTER_CLIENT)
        `when`(clientService.findById(CLIENT_WITHOUT_CHECKING_ACCOUNT.id)).thenReturn(CLIENT_WITHOUT_CHECKING_ACCOUNT)
    }

    private fun givenCheckingAccountService() {
        checkingAccountService = mock()
        `when`(checkingAccountService.findCheckingAccountByClientId(ACTIVE_CLIENT.id)).thenReturn(CHECKING_ACCOUNT)
        `when`(checkingAccountService.findCheckingAccountByClientId(DEFAULTER_CLIENT.id)).thenReturn(CHECKING_ACCOUNT)
        `when`(checkingAccountService.findCheckingAccountByClientId(CLIENT_WITHOUT_CHECKING_ACCOUNT.id)).thenReturn(null)
    }

    private fun givenGetPaymentMethodsByClient() {
        getPaymentMethodsByClient = GetPaymentMethodsByClient(paymentMethodService, clientService, checkingAccountService)
    }

    private fun whenGettingThePaymentMethodByClient(idClient: Long) {
        paymentMethodsByClient = getPaymentMethodsByClient.invoke(idClient)
    }

    private fun thenThePaymentMethodsHasFoundForActiveClient() {
        verify(paymentMethodService).findAllPaymentMethod()
        verify(clientService).findById(ACTIVE_CLIENT.id)
        verify(checkingAccountService).findCheckingAccountByClientId(ACTIVE_CLIENT.id)
        then(paymentMethodsByClient).isNotNull
    }

    private fun thenThePaymentMethodsHasFoundForDefaulterClient() {
        verify(paymentMethodService).findAllPaymentMethod()
        verify(clientService).findById(DEFAULTER_CLIENT.id)
        then(paymentMethodsByClient).isNotNull
    }

    private fun thenThePaymentMethodsHasFoundForClientWithoutCheckingAccount() {
        verify(paymentMethodService).findAllPaymentMethod()
        verify(clientService).findById(CLIENT_WITHOUT_CHECKING_ACCOUNT.id)
        verify(checkingAccountService).findCheckingAccountByClientId(CLIENT_WITHOUT_CHECKING_ACCOUNT.id)
        then(paymentMethodsByClient).isNotNull
    }

    private fun thenThePaymentMethodsForActiveHasReturned() {
        then(paymentMethodsByClient).isEqualTo(listOf(ACTIVE_PAYMENT_METHOD, ANOTHER_PAYMENT_METHOD))
    }

    private fun thenThePaymentMethodsForDefaulterClientHasReturned() {
        then(paymentMethodsByClient).isEqualTo(listOf(ACTIVE_PAYMENT_METHOD))
    }

    private fun thenThePaymentMethodsForClientWithoutCheckingAccountHasReturned() {
        then(paymentMethodsByClient).isEqualTo(listOf(ACTIVE_PAYMENT_METHOD))
    }

    private companion object {
        val ACTIVE_PAYMENT_METHOD = PaymentMethod(0L,"EFECTIVO", "EFECTIVO", "ACTIVE")
        val INACTIVE_PAYMENT_METHOD = PaymentMethod(1L,"TARJETA", "AHORA12", "INACTIVE")
        val ANOTHER_PAYMENT_METHOD = PaymentMethod(2L,"CUENTA_CORRIENTE", "CUENTA_CORRIENTE", "ACTIVE")

        val ACTIVE_CLIENT = Client(1L, "00000000", "First", "Last", "ACTIVE", 0.0, "", "", "")
        val DEFAULTER_CLIENT = Client(2L, "00000000", "First", "Last", "MOROSO", 0.0, "", "", "")
        val CLIENT_WITHOUT_CHECKING_ACCOUNT = Client(3L, "00000000", "First", "Last", "ACTIVE", 0.0, "", "", "")

        val CHECKING_ACCOUNT = CheckingAccount(0L, 1L, 0.0, 0.0, 0.0)

    }
}