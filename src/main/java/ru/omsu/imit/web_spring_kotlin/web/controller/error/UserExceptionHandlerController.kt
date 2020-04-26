package ru.omsu.imit.web_spring_kotlin.web.controller.error

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice

import org.springframework.ui.Model
import org.springframework.validation.BindException

import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.PasswordsDoesNotMatchException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.UserAlreadyExistsException

@ControllerAdvice("ru.omsu.imit.web_spring_kotlin.web.controller.user")
open class UserExceptionHandlerController {
    @ExceptionHandler(BindException::class)
    fun handleUserValidationExceptions(ex: BindException, model: Model): String {
        ex.fieldErrors.forEach { f -> model.addAttribute("${f.field}Error", f.defaultMessage) }
        return "SignUpPage"
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(ex: UserAlreadyExistsException, model: Model): String {
        model.addAttribute("usernameError", ex.message)
        return "SignUpPage"
    }

    @ExceptionHandler(PasswordsDoesNotMatchException::class)
    fun handlePasswordsDoesNotMatchException(ex: PasswordsDoesNotMatchException, model: Model): String {
        model.addAttribute("passwordError", ex.message)
        model.addAttribute("confirmPasswordError", ex.message)
        return "SignUpPage"
    }
}