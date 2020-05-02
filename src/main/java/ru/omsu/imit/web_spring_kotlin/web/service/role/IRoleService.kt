package ru.omsu.imit.web_spring_kotlin.web.service.role

import ru.omsu.imit.web_spring_kotlin.core.model.Role

interface IRoleService {
    fun getAllRole(): List<Role>
}