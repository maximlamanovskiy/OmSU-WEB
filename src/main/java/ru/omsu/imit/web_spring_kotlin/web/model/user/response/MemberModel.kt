package ru.omsu.imit.web_spring_kotlin.web.model.user.response

import ru.omsu.imit.web_spring_kotlin.core.model.Role
import ru.omsu.imit.web_spring_kotlin.core.model.User

open class Member(open val id: String, open val username: String, open val roles: String) {
    constructor(user: User) : this(user.id, user.username, getRoles(user.roles))
}

private fun getRoles(roles: List<Role>) : String {
    var res = ""
    roles.forEach { role -> res = res.plus("${role.role},")}
    if (res.isNotBlank()) {
        res = res.removeRange(res.length - 1, res.length)
    }
    return res
}