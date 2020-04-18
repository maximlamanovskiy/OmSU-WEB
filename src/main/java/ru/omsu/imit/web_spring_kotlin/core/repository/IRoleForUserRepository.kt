package ru.omsu.imit.web_spring_kotlin.core.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import ru.omsu.imit.web_spring_kotlin.core.model.RoleForUser

@Repository
interface IRoleForUserRepository: CrudRepository<RoleForUser, String> {
    fun findAllByUserId(userId: String): MutableIterable<RoleForUser>
}