package ru.omsu.imit.web_spring_kotlin.web.controller.error

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import org.springframework.ui.Model
import org.springframework.validation.BindException

import ru.omsu.imit.web_spring_kotlin.web.model.message.response.MessageResponse
import ru.omsu.imit.web_spring_kotlin.web.service.message.IMessageService

@ControllerAdvice("ru.omsu.imit.web_spring_kotlin.web.controller.message")
class MessageExceptionController
@Autowired
constructor(private val messageService: IMessageService) {
    @ExceptionHandler(BindException::class)
    fun handleUserValidationExceptions(ex: BindException, model: Model): String {
        ex.fieldErrors.forEach { f -> model.addAttribute("error", f.defaultMessage) }
        model.addAttribute("messages", messageService.getMessages().map { message -> MessageResponse(message) })
        return "ChatPage"
    }

    @ExceptionHandler(Exception::class)
    fun handleUserNotFoundException(ex: Exception, model: Model): String {
        model.addAttribute("error", ex.message)
        model.addAttribute("messages", messageService.getMessages().map { message -> MessageResponse(message) })
        return "ChatPage"
    }
}