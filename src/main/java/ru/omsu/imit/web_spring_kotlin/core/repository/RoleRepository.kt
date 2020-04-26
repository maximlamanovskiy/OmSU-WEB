package ru.omsu.imit.web_spring_kotlin.core.repository

import org.springframework.stereotype.Repository

import org.springframework.data.repository.CrudRepository
import java.util.Optional

import ru.omsu.imit.web_spring_kotlin.core.model.Role

@Repository
interface RoleRepository : CrudRepository<Role, String> {
    fun findRoleByRole(role: String): Optional<Role>
}