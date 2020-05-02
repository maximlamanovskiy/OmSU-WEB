package ru.omsu.imit.web_spring_kotlin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ru.omsu.imit.web_spring_kotlin.web.controller.interceptor.AdminActionsInterceptor

@Configuration
open class WebMvcConfig(
        @Value("\${spring.interceptor.logger.file}") private val file: String
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(AdminActionsInterceptor(file)).addPathPatterns("/users/**")
    }
}