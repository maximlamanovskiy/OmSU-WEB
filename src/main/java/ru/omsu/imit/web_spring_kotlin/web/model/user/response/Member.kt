package ru.omsu.imit.web_spring_kotlin.web.model.user.response

import ru.omsu.imit.web_spring_kotlin.core.model.Role

open class Member(open val username: String, open val roles: String) {
    constructor(username: String, roles: List<Role>) : this(username, getRoles(roles))
}

private fun getRoles(roles: List<Role>) : String {
    var res = ""
    roles.forEach { role -> res = res.plus("${role.role},")}
    if (res.isNotBlank()) {
        res = res.removeRange(res.length - 1, res.length)
    }
    return res
}