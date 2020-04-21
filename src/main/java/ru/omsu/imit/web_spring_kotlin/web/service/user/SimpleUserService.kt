package ru.omsu.imit.web_spring_kotlin.web.service.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.model.RoleForUser
import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleForUserRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.web.model.user.RegistrationModel
import java.lang.Exception

@Service
class SimpleUserService
@Autowired
constructor(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository,
        private val roleForUserRepository: RoleForUserRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun createUser(registrationModel: RegistrationModel): User {
        val newUser: User
        val password: String = registrationModel.password
        val userName: String = registrationModel.username

        if(userRepository.findUserByUsername(userName) != null) {
            throw Exception()
        }
        if(password != registrationModel.confirmPassword) {
            throw Exception()
        }

        newUser = User(userName, bCryptPasswordEncoder.encode(password))
        userRepository.save(newUser)

        val role = roleRepository.findRoleByRole(UserConstants.USER_ROLE)
        roleForUserRepository.save(RoleForUser(newUser.id, role.id))

        return newUser
    }
}