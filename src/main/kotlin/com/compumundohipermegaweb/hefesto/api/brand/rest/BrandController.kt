package com.compumundohipermegaweb.hefesto.api.brand.rest

import com.compumundohipermegaweb.hefesto.api.brand.domain.action.CreateBrand
import com.compumundohipermegaweb.hefesto.api.brand.domain.action.DeleteBrand
import com.compumundohipermegaweb.hefesto.api.brand.domain.action.FindAllBrands
import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/brands")
class BrandController(
        private val findAllBrands: FindAllBrands,
        private val deleteBrand: DeleteBrand,
        private val createBrand: CreateBrand) {

    @GetMapping
    fun getAllBrands(): ResponseEntity<FindAllBrandsResponse> {
        val brands = findAllBrands().map { it.toResponse() }
        return ResponseEntity.ok(FindAllBrandsResponse(brands))
    }

    @DeleteMapping("/{brandId}")
    fun physicalDeleteBrand(@PathVariable("brandId") brandId: Long): ResponseEntity<Any> {
        deleteBrand(brandId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun postBrand(@RequestBody request: CreateBrandRequest): ResponseEntity<BrandResponse> {
        val actionData = CreateBrand.ActionData(request.name)
        val brand = createBrand(actionData)
        return ResponseEntity.status(HttpStatus.CREATED).body(brand.toResponse())
    }

    private fun Brand.toResponse() = BrandResponse(id, name)
}