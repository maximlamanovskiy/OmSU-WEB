package ru.omsu.imit.web_spring_kotlin.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.model.RoleForUser
import ru.omsu.imit.web_spring_kotlin.core.model.User

import ru.omsu.imit.web_spring_kotlin.core.repository.IUserRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.IRoleRepository
import ru.omsu.imit.web_spring_kotlin.core.repository.IRoleForUserRepository

@Component
class InitData(
        @Autowired _userRepository: IUserRepository,
        @Autowired _roleRepository: IRoleRepository,
        @Autowired _roleForUserRepository: IRoleForUserRepository
        ): ApplicationRunner {
    private val userRepository: IUserRepository = _userRepository
    private val roleRepository: IRoleRepository = _roleRepository
    private val roleForUserRepository: IRoleForUserRepository = _roleForUserRepository

    override fun run(args: ApplicationArguments?) {
        var user: User
        var role: Role
        var roleForUser: RoleForUser

        val count: Long = userRepository.count()

        if(count == 0L) {
            user = User("user", "\$2a\$10\$Y8CmVrfouwgboub5/scfVOIVfR4Q0M43HMwwH3bV8GLvoCy7XSBvm") //userpass
            role = Role("USER")
            roleForUser = RoleForUser(user.getId(), role.getId())

            userRepository.save(user)
            roleRepository.save(role)
            roleForUserRepository.save(roleForUser)

            user = User("admin", "\$2a\$10\$Vtu63/1FUyHZSmK/uQK.9.1oOqTIL4IAE8QAI.SkSJHG09eO.cNdi") //adminpass
            roleForUser = RoleForUser(user.getId(), role.getId())

            userRepository.save(user)
            roleForUserRepository.save(roleForUser)

            role = Role("ADMIN")
            roleForUser = RoleForUser(user.getId(), role.getId())

            roleRepository.save(role)
            roleForUserRepository.save(roleForUser)
        }
    }
}