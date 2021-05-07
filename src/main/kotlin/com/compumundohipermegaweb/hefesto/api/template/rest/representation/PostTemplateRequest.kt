package com.compumundohipermegaweb.hefesto.api.template.rest.representation

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Aca se definen las representaciones de los diferentes request y response que puede recibir nuestra api.
 * Es importante anotar cada variable de nuestra data class con @JsonProperty("value_en_el_json") para evitar rupturas
 * en el protocolo en caso de refactor u otros y para asegurar el correcto parseo
 *
 * @JsonIgnoreProperties esta anotacion se utiliza para avisarle a jackson que propiedades debe ignorar, en general es
 * buena practica ponerle ignoewUnknoun = true para evitar que rompa la deserializacion en caso de que llegue un campo
 * inesperado. Por lo general se usa en las representaciones de los Request, no de los Response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class PostTemplateRequest(
        @JsonProperty("input") val input: String,
        @JsonProperty("output") val output: String
)
