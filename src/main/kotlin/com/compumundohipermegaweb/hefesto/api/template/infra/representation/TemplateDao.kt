package com.compumundohipermegaweb.hefesto.api.template.infra.representation

import javax.persistence.*

/**
 * DAO = Data access object (objeto de acceso a datos)
 * Un DAO es el mapeo de un POJO (Plain Old Java Object) a una tabla de la base de datos. Esto significa que nuestro
 * ORM (Object Relational Mapping) va a generar una relacion entre nuestro Objeto y una tabla en la BBDD
 * Cuando creamos un DAO basicamente estamos explicandole a la JPA (Java Persistence API) que la base de datos a la que
 * nos conectemos va a contener una tabla con el nombre y columnas que nosotros definamos en nuestra clase.
 * Es una data class y no una class a secas ya que al especificar que es data Kotlin nos genera getters, setters y toString
 *
 * Requisitos para crear correctamente un DAO
 *
 * Anotaciones a nivel clase:
 * @Entity todo DAO debe ser anotado con esta anotation para especificarle al ORM que es una entidad a ser mapear
 * @Table especifica la tabla primaria del elemento, es decir, contra que tabla de la BBDD se va a mapear nuestro objeto
 *
 * Anotaciones a nivel campo/variable:
 * @Id indica que el campo es la primary key de nuestro objeto
 * @GeneratedValue indica que la primary key no es generada por nosotros. Se le debe especificar un "strategy", en nuestro
 * caso vamos a usar GenerationType.IDENTITY delegando la creacion del ID a la BBDD
 * @Column (opcional) columna correspondiente en la tabla. Si no se setea se mapea por el nombre de la variable
 *
 * Existen otros tipos de anotaciones pero no vamos a profundizar ya que es muy probables que no los usemos
 */
@Entity
@Table(name = "TEMPLATE")
data class TemplateDao(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name ="ID") val id: Long,
        @Column(name = "INPUT") val input: String,
        @Column(name = "OUTPUT") val output: String
)
