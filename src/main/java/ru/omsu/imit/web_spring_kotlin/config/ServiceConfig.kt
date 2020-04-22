package ru.omsu.imit.web_spring_kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleForUserRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService
import ru.omsu.imit.web_spring_kotlin.web.service.user.SimpleUserService

@Configuration
open class ServiceConfig {
    @Bean
    open fun configureUserService(
            userRepository: UserRepository,
            roleRepository: RoleRepository,
            roleForUserRepository: RoleForUserRepository,
            bCryptPasswordEncoder: BCryptPasswordEncoder
    ): IUserService {
        return SimpleUserService(userRepository, roleRepository, roleForUserRepository, bCryptPasswordEncoder)
    }
}