package com.compumundohipermegaweb.hefesto.api.category.infra.repository

import com.compumundohipermegaweb.hefesto.api.category.infra.representation.CategoryRepresentation
import org.springframework.data.repository.CrudRepository

interface CategoryDao: CrudRepository<CategoryRepresentation, Long>
