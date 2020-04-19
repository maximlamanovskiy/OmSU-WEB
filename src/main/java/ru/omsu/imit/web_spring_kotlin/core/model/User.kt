package ru.omsu.imit.web_spring_kotlin.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.ManyToMany
import javax.persistence.FetchType
import javax.persistence.CascadeType
import javax.persistence.JoinTable
import javax.persistence.JoinColumn


@Entity
@Table(name = "USERS")
open class User constructor(
        @Id open val id: String,
        @Column(name = "username", nullable = false) open val username: String,
        @JsonIgnore @Column(name = "password", nullable = false) open val password: String,
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "ROLE_FOR_USER",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
        open val roles: List<Role> = mutableListOf()
) {
    constructor(_username: String, _password: String) : this(UUID.randomUUID().toString(), _username, _password)
    constructor(): this("", "", "")
}