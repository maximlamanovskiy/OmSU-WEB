package ru.omsu.imit.web_spring_kotlin.core.model

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Role_For_User")
class RoleForUser(_id: String, _userId: String, _roleId: String) {
    @Id
    private val id: String = _id

    @Column(name = "user_id", nullable = false)
    private val userId: String = _userId

    @Column(name = "role_id", nullable = false)
    private val roleId: String = _roleId

    constructor(_userId: String, _roleId: String): this(UUID.randomUUID().toString(), _userId, _roleId)

    fun getId(): String {
        return id
    }

    fun getUserId(): String {
        return userId
    }

    fun getRoleId(): String {
        return roleId
    }
}