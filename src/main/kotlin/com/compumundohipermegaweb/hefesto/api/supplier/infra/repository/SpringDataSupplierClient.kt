package com.compumundohipermegaweb.hefesto.api.supplier.infra.repository

import com.compumundohipermegaweb.hefesto.api.supplier.infra.representation.SupplierDao
import org.springframework.data.repository.CrudRepository

interface SpringDataSupplierClient : CrudRepository <SupplierDao, Long>  {

}