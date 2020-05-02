package ru.omsu.imit.web_spring_kotlin.web.model.message.response

import ru.omsu.imit.web_spring_kotlin.core.model.Message

open class MessageResponse(
        open val message: String,
        open val owner: String
) {
    constructor(_message: Message) : this(_message.message, _message.owner.username)
}