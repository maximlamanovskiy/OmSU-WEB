package ru.omsu.imit.web_spring_kotlin.core.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import ru.omsu.imit.web_spring_kotlin.core.model.User

@Repository
interface UserRepository: CrudRepository<User, String> {
    fun findUserByUsername(username: String): User
}