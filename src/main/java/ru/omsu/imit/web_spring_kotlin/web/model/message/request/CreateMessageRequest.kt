package ru.omsu.imit.web_spring_kotlin.web.model.message.request

import javax.validation.constraints.NotNull

open class CreateMessageRequest(
        @get:NotNull(message = "Message should be not null")
        open val message: String
)
