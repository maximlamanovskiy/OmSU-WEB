package ru.omsu.imit.web_spring_kotlin.web.model.user.response

import ru.omsu.imit.web_spring_kotlin.core.model.Role

open class RoleResponse(open val id: String, open val role: String) {
    constructor(role: Role): this(role.id, role.role)
}