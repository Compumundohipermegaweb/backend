package com.compumundohipermegaweb.hefesto.api.brand.rest

import com.compumundohipermegaweb.hefesto.api.brand.domain.action.FindAllBrands
import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/brands")
class BrandController(private val findAllBrands: FindAllBrands) {

    @GetMapping
    fun getAllBrands(): ResponseEntity<FindAllBrandsResponse> {
        val brands = findAllBrands().map { it.toResponse() }
        return ResponseEntity.ok(FindAllBrandsResponse(brands))
    }

    private fun Brand.toResponse() = BrandResponse(id, name)
}