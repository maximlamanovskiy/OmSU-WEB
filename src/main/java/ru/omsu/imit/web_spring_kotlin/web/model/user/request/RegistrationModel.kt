package ru.omsu.imit.web_spring_kotlin.web.model.user.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

open class RegistrationModel(
        @get:NotNull(message = "Username must be not null")
        @get:Size(min = 3, max = 15, message = "Username should be from 3 to 15 symbols")
        open val username: String,
        @get:NotNull(message = "Password must be not null")
        @get:Size(min = 5, max = 50, message = "Password should be from 5 to 50 symbols")
        open val password: String,
        @get:NotNull(message = "Confirm password must be not null")
        @get:Size(min = 5, max = 50, message = "Confirm password should be from 5 to 50 symbols")
        open val confirmPassword: String
)