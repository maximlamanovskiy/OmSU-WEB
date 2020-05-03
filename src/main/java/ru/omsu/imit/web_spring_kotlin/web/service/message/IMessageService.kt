package ru.omsu.imit.web_spring_kotlin.web.service.message

import ru.omsu.imit.web_spring_kotlin.core.model.Message
import ru.omsu.imit.web_spring_kotlin.web.model.message.request.CreateMessageRequest

interface IMessageService {
    fun createMessage(createMessageRequest: CreateMessageRequest, username: String): Message
    fun getMessages(): List<Message>
}