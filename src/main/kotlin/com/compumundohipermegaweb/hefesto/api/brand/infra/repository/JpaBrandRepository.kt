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
        val brandsRepresentation = brandDao.findAllByDeleted(false)
        return brandsRepresentation.map { it.toBrand() }
    }

    override fun delete(brandId: Long) {
        brandDao.updateDeletedById(brandId)
    }

    override fun save(brand: Brand): Brand {
        val brandRepresentation = brand.toRepresentation()
        return brandDao.save(brandRepresentation).toBrand()
    }

    override fun update(brand: Brand): Brand {
        val brandRepresentation = brand.toRepresentation()
        return brandDao.save(brandRepresentation).toBrand()
    }

    private fun Brand.toRepresentation() = BrandRepresentation(id, name, false)

    private fun BrandRepresentation.toBrand()= Brand(id, name)
}
