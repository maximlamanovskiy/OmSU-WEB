package ru.omsu.imit.web_spring_kotlin.web.model.user

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

open class RegistrationModel(
        @get:NotNull
        @get:Size(min = 3, max = 15)
        open val username: String,
        @get:NotNull
        @get:Size(min = 5, max = 50)
        open val password: String,
        @get:NotNull
        @get:Size(min = 5, max = 50)
        open val confirmPassword: String
)