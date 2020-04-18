package ru.omsu.imit.web_spring_kotlin.core.model

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ROLE_FOR_USER")
open class RoleForUser constructor(
        @Id open val id: String,
        @Column(name = "user_id", nullable = false) open val userId: String,
        @Column(name = "role_id", nullable = false) open val roleId: String
) {
    constructor(_userId: String, _roleId: String) : this(UUID.randomUUID().toString(), _userId, _roleId)
    constructor(): this("", "", "")
}