package ru.omsu.imit.web_spring_kotlin.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "USERS")
open class User constructor(
        @Id open val id: String,
        @Column(name = "username", nullable = false) open val username: String,
        @JsonIgnore @Column(name = "password", nullable = false) open val password: String
) {
    constructor(_username: String, _password: String) : this(UUID.randomUUID().toString(), _username, _password)
    constructor(): this("", "", "")
}