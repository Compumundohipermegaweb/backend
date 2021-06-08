package com.compumundohipermegaweb.hefesto.api.authentication.rest

import com.compumundohipermegaweb.hefesto.api.authentication.domain.action.Login
import com.compumundohipermegaweb.hefesto.api.authentication.domain.model.Session
import com.compumundohipermegaweb.hefesto.api.authentication.rest.representation.LoginRequest
import com.compumundohipermegaweb.hefesto.api.authentication.rest.representation.LoginResponse
import com.compumundohipermegaweb.hefesto.api.authentication.rest.representation.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/authentication")
class AuthenticationController(private val login: Login) {

    @PostMapping("/login")
    fun post(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val session = login(request.toActionData())
        return ResponseEntity.ok(session.toResponse())
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    private fun LoginRequest.toActionData() = Login.ActionData(username, password)
    private fun Session.toResponse() = LoginResponse(user, TokenResponse(token.code, token.role.name))
}