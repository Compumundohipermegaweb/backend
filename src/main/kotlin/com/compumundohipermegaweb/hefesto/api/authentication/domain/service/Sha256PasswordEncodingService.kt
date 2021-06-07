package com.compumundohipermegaweb.hefesto.api.authentication.domain.service

import java.security.MessageDigest

class Sha256PasswordEncodingService: PasswordEncodingService {

    override fun encode(password: String): String {
        return MessageDigest.getInstance("SHA-256")
            .digest(password.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
    }
}