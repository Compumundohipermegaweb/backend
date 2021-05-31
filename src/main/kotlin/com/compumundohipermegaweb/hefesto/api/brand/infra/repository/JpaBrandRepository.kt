package com.compumundohipermegaweb.hefesto.api.brand.infra.repository

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.representation.BrandRepresentation

class JpaBrandRepository(private val brandDao: BrandDao): BrandRepository {

    override fun findById(id: Long): Brand? {
        val brandDao = brandDao.findById(id)

        return if (brandDao.isPresent) { brandDao.get().toBrand() } else { null }
    }

    override fun findAll(): List<Brand> {
        val brandsRepresentation = brandDao.findAll()
        return brandsRepresentation.map { it.toBrand() }
    }

    private fun BrandRepresentation.toBrand(): Brand {
        return Brand(id, name)
    }
}
