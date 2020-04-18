package ru.omsu.imit.web_spring_kotlin.core.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ROLE")
class Role(_id: String, _role: String) {

    @Id
    private val id: String = _id

    @Column(name = "role", nullable = false)
    private val role: String = _role

    constructor(_role: String): this(UUID.randomUUID().toString(), _role)

    fun getId(): String {
        return id
    }

    fun getRole(): String {
        return role
    }
}