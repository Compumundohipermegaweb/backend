package com.compumundohipermegaweb.hefesto.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Configuration
@EnableWebMvc
class WebConfiguration: WebMvcConfigurer {

	override fun addCorsMappings(registry: CorsRegistry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:4200", "https://hefesto-dev.web.app")
				.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
	}
}
