package ru.omsu.imit.web_spring_kotlin.web.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.model.RoleForUser
import ru.omsu.imit.web_spring_kotlin.core.model.User

import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleForUserRepository

@Component
class InitData
@Autowired
constructor(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository,
        private val roleForUserRepository: RoleForUserRepository
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        var user: User
        var role: Role
        var roleForUser: RoleForUser

        val count: Long = userRepository.count()

        if(count == 0L) {
            user = User("user", "\$2a\$10\$Y8CmVrfouwgboub5/scfVOIVfR4Q0M43HMwwH3bV8GLvoCy7XSBvm") //userpass
            role = Role("USER")
            roleForUser = RoleForUser(user.id, role.id)

            userRepository.save(user)
            roleRepository.save(role)
            roleForUserRepository.save(roleForUser)

            user = User("admin", "\$2a\$10\$Vtu63/1FUyHZSmK/uQK.9.1oOqTIL4IAE8QAI.SkSJHG09eO.cNdi") //adminpass
            roleForUser = RoleForUser(user.id, role.id)

            userRepository.save(user)
            roleForUserRepository.save(roleForUser)

            role = Role("ADMIN")
            roleForUser = RoleForUser(user.id, role.id)

            roleRepository.save(role)
            roleForUserRepository.save(roleForUser)
        }
    }
}