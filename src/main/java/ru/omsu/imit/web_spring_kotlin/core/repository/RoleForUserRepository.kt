package ru.omsu.imit.web_spring_kotlin.core.repository

import org.springframework.stereotype.Repository

import org.springframework.data.repository.CrudRepository

import ru.omsu.imit.web_spring_kotlin.core.model.RoleForUser

@Repository
interface RoleForUserRepository: CrudRepository<RoleForUser, String> {
    fun findRoleForUsersByUserId(userId: String): MutableIterable<RoleForUser>
}