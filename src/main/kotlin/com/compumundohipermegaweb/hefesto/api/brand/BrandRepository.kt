package com.compumundohipermegaweb.hefesto.api.brand

interface BrandRepository {
    fun findById(id: Long): Brand

}
