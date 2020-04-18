package ru.omsu.imit.web_spring_kotlin.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "USERS")
open class User(_id: String, _username: String, _password: String) {

    @Id
    private val id: String = _id

    @Column(name = "username", nullable = false)
    private val username: String = _username

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private val password: String = _password

    constructor(_username: String, _password: String) : this(UUID.randomUUID().toString(), _username, _password)

    fun getId(): String {
        return id
    }

    fun getUsername(): String {
        return username
    }

    fun getPassword(): String {
        return password
    }
}