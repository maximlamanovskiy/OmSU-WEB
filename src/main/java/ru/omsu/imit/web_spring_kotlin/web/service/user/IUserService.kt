package ru.omsu.imit.web_spring_kotlin.web.service.user

import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.web.model.user.request.RegistrationModel
import ru.omsu.imit.web_spring_kotlin.web.model.user.request.UpdateUserModel

interface IUserService {
    companion object UserConstants {
        const val USER_ROLE = "USER"
        const val ADMIN_ROLE = "ADMIN"
    }

    fun createUser(registrationModel: RegistrationModel): User
    fun getAllUsers(): List<User>
    fun getUserById(userId: String): User
    fun updateUser(userId: String, updateUserModel: UpdateUserModel): User
}