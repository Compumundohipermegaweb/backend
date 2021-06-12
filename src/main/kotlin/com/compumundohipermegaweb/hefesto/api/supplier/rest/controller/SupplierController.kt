package com.compumundohipermegaweb.hefesto.api.supplier.rest.controller

import com.compumundohipermegaweb.hefesto.api.supplier.domain.action.RegisterSupplier
import com.compumundohipermegaweb.hefesto.api.supplier.domain.model.Supplier
import com.compumundohipermegaweb.hefesto.api.supplier.rest.representation.PostSupplierRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/suppliers")
class SupplierController (private val registerSupplier: RegisterSupplier) {

    @PostMapping
    fun postSupplier(@RequestBody body: PostSupplierRequest): ResponseEntity<Supplier> {

        val supplier = registerSupplier(body.toSupplier())

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(supplier.id)
                .toUri()
        ).body(supplier)
    }

    private fun PostSupplierRequest.toSupplier() = Supplier(0L, organization, contactName, contactNumber, email, cuit)
}

