package ru.omsu.imit.web_spring_kotlin.web.controller.error

import org.springframework.ui.Model
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice("ru.omsu.imit.web_spring_kotlin.web.controller.user")
open class UserExceptionHandlerController {
    @ExceptionHandler(BindException::class)
    fun handleAllExceptions(ex: BindException, model: Model): String {
        ex.fieldErrors.forEach { f -> model.addAttribute("${f.field}Error", f.defaultMessage) }
        return "SignUpPage"
    }
}