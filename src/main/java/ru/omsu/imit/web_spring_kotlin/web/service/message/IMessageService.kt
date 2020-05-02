package ru.omsu.imit.web_spring_kotlin.web.service.message

import java.security.Principal

import ru.omsu.imit.web_spring_kotlin.core.model.Message
import ru.omsu.imit.web_spring_kotlin.web.model.message.request.CreateMessageRequest

interface IMessageService {
    fun createMessage(createMessageRequest: CreateMessageRequest, principal: Principal): Message
    fun getMessages(): List<Message>
}