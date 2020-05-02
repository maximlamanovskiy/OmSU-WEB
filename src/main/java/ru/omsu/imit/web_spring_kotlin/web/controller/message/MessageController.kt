package ru.omsu.imit.web_spring_kotlin.web.controller.message

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ModelAttribute
import javax.validation.Valid

import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod
import java.security.Principal

import ru.omsu.imit.web_spring_kotlin.web.model.message.request.CreateMessageRequest
import ru.omsu.imit.web_spring_kotlin.web.model.message.response.MessageResponse
import ru.omsu.imit.web_spring_kotlin.web.service.message.IMessageService

@Controller
@RequestMapping("/chat")
open class MessageController
@Autowired
constructor(private val messageService: IMessageService) {

    @Transactional
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    open fun openChat(model: Model): String {
        model.addAttribute("messages", messageService.getMessages().map { message -> MessageResponse(message) })
        return "ChatPage"
    }

    @Transactional
    @RequestMapping(method = [RequestMethod.POST], produces = [MediaType.TEXT_HTML_VALUE])
    open fun updateUser(@Valid @ModelAttribute createMessageRequest: CreateMessageRequest, principal: Principal): String {
        messageService.createMessage(createMessageRequest, principal.name)
        return "redirect:/chat"
    }
}