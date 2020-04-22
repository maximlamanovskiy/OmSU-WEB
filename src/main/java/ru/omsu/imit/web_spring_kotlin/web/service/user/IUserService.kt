package ru.omsu.imit.web_spring_kotlin.web.service.user

import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.web.model.user.RegistrationModel

interface IUserService {
    fun createUser(registrationModel: RegistrationModel): User
    fun getAllUsers(): List<User>
    fun getUserById(userId: String): User
}