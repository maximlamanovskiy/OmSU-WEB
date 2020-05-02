package ru.omsu.imit.web_spring_kotlin.web.controller.interceptor

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

import ru.omsu.imit.web_spring_kotlin.web.model.response.FinalResponse


@ControllerAdvice("ru.omsu.imit.web_spring_kotlin.web.controller.user.v2")
class UserResponseBodyAdvice : ResponseBodyAdvice<Any> {
    override fun supports(p0: MethodParameter, p1: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(data: Any?, methodParameter: MethodParameter, mediaType: MediaType, cl: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): Any? {
        return FinalResponse(data, true)
    }


    @ExceptionHandler(Exception::class)
    fun handleUserValidationExceptions(ex: Exception): ResponseEntity<FinalResponse<String>> {
        return ResponseEntity.ok(FinalResponse(ex.message!!, false))
    }
}