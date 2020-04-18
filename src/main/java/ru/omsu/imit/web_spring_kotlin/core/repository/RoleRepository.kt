package ru.omsu.imit.web_spring_kotlin.core.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import ru.omsu.imit.web_spring_kotlin.core.model.Role

@Repository
interface RoleRepository: CrudRepository<Role, String>