package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class Sha256PasswordEncodingServiceShould {

    private lateinit var passwordEncodingService: PasswordEncodingService

    private lateinit var encoded: String

    @Test
    fun `encode using sha256` () {
        passwordEncodingService = Sha256PasswordEncodingService()

        encoded = passwordEncodingService.encode("secret")

        then(encoded).isEqualTo("2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b")
    }
}