package ru.omsu.imit.web_spring_kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import ru.omsu.imit.web_spring_kotlin.core.repository.MessageRepository

import ru.omsu.imit.web_spring_kotlin.core.repository.RoleRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.web.service.message.IMessageService
import ru.omsu.imit.web_spring_kotlin.web.service.message.SimpleMessageService
import ru.omsu.imit.web_spring_kotlin.web.service.role.IRoleService
import ru.omsu.imit.web_spring_kotlin.web.service.role.SimpleRoleService
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService
import ru.omsu.imit.web_spring_kotlin.web.service.user.SimpleUserService

@Configuration
open class ServiceConfig {
    @Bean
    open fun configureUserService(
            userRepository: UserRepository,
            roleRepository: RoleRepository,
            bCryptPasswordEncoder: BCryptPasswordEncoder
    ): IUserService {
        return SimpleUserService(userRepository, roleRepository, bCryptPasswordEncoder)
    }

    @Bean
    open fun configureRoleService(repository: RoleRepository) : IRoleService {
        return SimpleRoleService(repository)
    }

    @Bean
    open fun configureMessageService(
            messageRepository: MessageRepository,
            userRepository: UserRepository
    ) : IMessageService {
        return SimpleMessageService(messageRepository, userRepository)
    }
}