package ru.omsu.imit.web_spring_kotlin.web.service.message

import java.security.Principal

import ru.omsu.imit.web_spring_kotlin.core.model.Message
import ru.omsu.imit.web_spring_kotlin.core.repository.MessageRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.web.model.message.request.CreateMessageRequest
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.UserNotFoundException

class SimpleMessageService(
        private val messageRepository: MessageRepository,
        private val userRepository: UserRepository
) : IMessageService {
    override fun createMessage(createMessageRequest: CreateMessageRequest, principal: Principal): Message {
        val user = userRepository.findUserByUsername(principal.name) ?: throw UserNotFoundException("Unable to find user")
        return messageRepository.save(Message(createMessageRequest.message, user))
    }

    override fun getMessages(): List<Message> {
        return messageRepository.findAll().toList()
    }
}