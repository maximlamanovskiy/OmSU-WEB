package ru.omsu.imit.web_spring_kotlin.web.service.user

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.lang.Exception

import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.model.RoleForUser
import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleForUserRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.web.model.user.RegistrationModel
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService.UserConstants.USER_ROLE
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.PasswordsDoesNotMatchException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.UserAlreadyExistsException

class SimpleUserService
constructor(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository,
        private val roleForUserRepository: RoleForUserRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : IUserService {
    override fun createUser(registrationModel: RegistrationModel): User {
        val newUser: User
        val password: String = registrationModel.password
        val userName: String = registrationModel.username

        if (userRepository.findUserByUsername(userName) != null) {
            throw UserAlreadyExistsException("Unable to register user. User already exists.")
        }
        if (password != registrationModel.confirmPassword) {
            throw PasswordsDoesNotMatchException("Passwords doesn't match")
        }

        newUser = User(userName, bCryptPasswordEncoder.encode(password))
        userRepository.save(newUser)

        val role: Role = roleRepository.findRoleByRole(USER_ROLE)
        roleForUserRepository.save(RoleForUser(newUser.id, role.id))

        return newUser
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll().toList()
    }

    override fun getUserById(userId: String): User {
        return userRepository.findById(userId).orElseThrow { Exception() }
    }
}