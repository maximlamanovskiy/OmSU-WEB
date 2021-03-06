package ru.omsu.imit.web_spring_kotlin.core.service.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.core.service.user.exception.UserNotFoundException

@Service
open class SimpleUserDetailsService
@Autowired
constructor(private val userRepository: UserRepository) : UserDetailsService {
    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findUserByUsername(username!!) ?: throw UserNotFoundException()
        return User(user.username, user.password, user.roles.map { role: Role -> SimpleGrantedAuthority(role.role) })
    }
}