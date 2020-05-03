package ru.omsu.imit.web_spring_kotlin.web.model.message.request

import javax.validation.constraints.NotBlank

open class CreateMessageRequest(
        @get:NotBlank(message = "Message should be not blank")
        open val message: String
)
