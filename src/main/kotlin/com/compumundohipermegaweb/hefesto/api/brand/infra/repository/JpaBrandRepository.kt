package com.compumundohipermegaweb.hefesto.api.brand.infra.repository

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository
import com.compumundohipermegaweb.hefesto.api.brand.infra.representation.BrandDao

class JpaBrandRepository(private val springDataBrandClient: SpringDataBrandClient): BrandRepository {

    override fun findById(id: Long): Brand? {
        val brandDao = springDataBrandClient.findById(id)

        return if (brandDao.isPresent) { brandDao.get().toBrand() } else { null }
    }

    private fun BrandDao.toBrand(): Brand {
        return Brand(id, name)
    }
}
