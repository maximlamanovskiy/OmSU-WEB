package ru.omsu.imit.web_spring_kotlin.web.model.message.response

import ru.omsu.imit.web_spring_kotlin.core.model.Message
import ru.omsu.imit.web_spring_kotlin.core.model.User

open class MessageResponse(
        open val message: String,
        open val owner: String
) {
    constructor(_message: Message, _owner: User) : this(_message.message, _owner.username)
}