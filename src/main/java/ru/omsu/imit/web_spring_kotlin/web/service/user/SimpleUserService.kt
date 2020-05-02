package ru.omsu.imit.web_spring_kotlin.web.service.user

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.web.model.user.request.RegistrationModel
import ru.omsu.imit.web_spring_kotlin.web.model.user.request.UpdateUserModel
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService.UserConstants.ADMIN_ROLE
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService.UserConstants.USER_ROLE
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.UserNotFoundException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.UserAlreadyExistsException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.RoleNotFoundException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.InvalidUpdateUserRequestException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.PasswordsDoesNotMatchException

class SimpleUserService(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : IUserService {
    override fun createUser(registrationModel: RegistrationModel): User {
        val roles: Set<Role> = mutableSetOf()
        val password: String = registrationModel.password
        val userName: String = registrationModel.username

        if (userRepository.findUserByUsername(userName) != null) {
            throw UserAlreadyExistsException("Unable to register user. User already exists.")
        }
        if (password != registrationModel.confirmPassword) {
            throw PasswordsDoesNotMatchException("Passwords doesn't match")
        }

        val userRole = roleRepository.findRoleByRole(USER_ROLE).orElseThrow { throw RoleNotFoundException("Unable to find role") }
        val newUser = User(userName, bCryptPasswordEncoder.encode(password), roles.plus(userRole))
        userRepository.save(newUser)

        return newUser
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll().toList()
    }

    override fun getUserById(userId: String): User {
        return userRepository.findById(userId).orElseThrow { throw UserNotFoundException("Unable to find user") }
    }

    override fun updateUser(userId: String, updateUserModel: UpdateUserModel): User {
        val newUsername = updateUserModel.username
        val newPassword = updateUserModel.password
        val admin = updateUserModel.ADMIN
        val user = updateUserModel.USER
        if (!user && !admin) {
            throw InvalidUpdateUserRequestException("User should have at least one role")
        }

        println(newPassword.isNotBlank())
        val oldUser: User = userRepository.findById(userId).orElseThrow { throw UserNotFoundException("Unable to find user") }
        var password = oldUser.password

        if (oldUser.username != newUsername && userRepository.findUserByUsername(newUsername) != null) {
            throw UserAlreadyExistsException("Unable to update user. Such name already in use")
        }
        if (newPassword != updateUserModel.confirmPassword) {
            throw PasswordsDoesNotMatchException("Passwords doesn't match")
        }

        if (newPassword.isNotBlank()) {
            password = bCryptPasswordEncoder.encode(newPassword)
        }

        val userRoles = ArrayList<Role>(2)
        if (user) userRoles
                .add(roleRepository.findRoleByRole(USER_ROLE)
                        .orElseThrow { throw UserNotFoundException("Unable to find user") })
        if (admin) userRoles
                .add(roleRepository.findRoleByRole(ADMIN_ROLE)
                        .orElseThrow { throw UserNotFoundException("Unable to find user") })
        val roles = oldUser.roles.intersect(userRoles).plus(userRoles)

        println(password)
        val newUser = User(userId, newUsername, password, roles)
        userRepository.save(newUser)
        return oldUser
    }
}