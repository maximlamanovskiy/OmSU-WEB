package ru.omsu.imit.web_spring_kotlin.web.model.user.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

open class UpdateUserModel(
        @get:NotNull(message = "Username must be not null")
        @get:Size(min = 3, max = 15, message = "Username should be from 3 to 15 symbols")
        open val username: String,
        open val password: String?,
        open val confirmPassword: String?,
        open val ADMIN: Boolean = false,
        open val USER: Boolean = false
)