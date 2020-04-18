package ru.omsu.imit.web_spring_kotlin.core.model

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ROLE")
open class Role constructor(
        @Id open val id: String,
        @Column(name = "role", nullable = false) open val role: String
) {
    constructor(_role: String) : this(UUID.randomUUID().toString(), _role)
    constructor(): this("", "")
}