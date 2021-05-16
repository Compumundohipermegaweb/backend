package com.compumundohipermegaweb.hefesto.api.brand.infra

import com.compumundohipermegaweb.hefesto.api.brand.domain.model.Brand
import com.compumundohipermegaweb.hefesto.api.brand.domain.repository.BrandRepository

class JpaBrandRepository(private val springDataBrandClient: SpringDataBrandClient): BrandRepository {

    override fun findById(id: Long): Brand? {
        val brandDao = springDataBrandClient.findById(id)

        return if (brandDao.isPresent) { brandDao.get().toBrand() } else { null }
    }

    private fun BrandDao.toBrand(): Brand {
        return Brand(id, name)
    }
}
