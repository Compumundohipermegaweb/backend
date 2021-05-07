package com.compumundohipermegaweb.hefesto.api.template.infra.repository

import com.compumundohipermegaweb.hefesto.api.template.infra.representation.TemplateDao
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

/**
 * Los "Client" proveen acceso a la base de datos. Los mismos deberan ser declarados como un interface que herede alguno
 * de los repositorios de Spring Data, el mas comun es CrudRepository ya que provee todas las operaciones basicas
 * Los parametros que recibe el CrudRepository<T, ID> son:
 * T = Tipo de la representación a la que provee acceso
 * ID = Tipo del @Id de la representación
 *
 * Para agregarle funcionalidades a un Client se puede realizar de las siguientes maneras
 * 1. Query methods: Se crean definiendo funciones con un patron de nombardo especifico (mas info -> https://docs.spring.io/spring-data/jpa/docs/1.8.x/reference/html/#repositories.query-methods.query-creation)
 * 2. @Query + JPQL: Para utilizar este metodo se debe anotar una funcion con @Query y escribir entre parentesis la
 * query que debe ser ejecutada en JPQL (Java Persisten Query Language). A diferencia de las Query methods esta forma de
 * declarar querys no depende del nombre de la funcion por lo que podemos ponerle el mobre que queramos (mas info -> https://docs.spring.io/spring-data/jpa/docs/1.8.x/reference/html/#jpa.query-methods.at-query)
 * 3. @Query + SQL Nativo: Del mismo modo que podemos usar JPQL dentro de una @Query se puede utilizar SQL nativo, solo
 * que se tiene que especificar mediante el parametro "nativeQuery = true" y escribir la query en "value = "QUERY""
 *
 * Abajo dejo ejemplo de 3 funciones que realizan la MISMA query pero utilizando metodos diferentes
 */
interface SpringDataTemplateClient: CrudRepository<TemplateDao, Long> {

    /**
     * Query method
     */
    fun findByInput(input: String): TemplateDao?

    /**
     * JPQL
     */
    @Query("select t from TemplateDao t where t.input = :input")
    fun cachitoTraemeElCosoPorInput(input: String): TemplateDao?

    @Query(nativeQuery = true,
    value = "SELECT * FROM TEMPLATE WHERE INPUT = ?0")
    fun queryNativa(input: String): TemplateDao?

}
