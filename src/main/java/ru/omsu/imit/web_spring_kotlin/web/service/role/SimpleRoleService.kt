package ru.omsu.imit.web_spring_kotlin.web.service.role

import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.repository.RoleRepository


class SimpleRoleService(
        private val repository: RoleRepository
) : IRoleService {
    override fun getAllRole(): List<Role> {
        return repository.findAll().toList()
    }
}