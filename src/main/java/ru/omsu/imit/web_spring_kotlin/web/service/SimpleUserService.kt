package ru.omsu.imit.web_spring_kotlin.web.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.web.model.user.RegistrationModel
import java.lang.Exception

@Service
class SimpleUserService
@Autowired
constructor(
        private val userRepository: UserRepository,
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
        return newUser
    }
}